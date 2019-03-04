package com.olvind.tso
package ts
package transforms

import com.olvind.tso.seqs.TraversableOps
import com.olvind.tso.ts.TsTreeScope.LoopDetector

/**
  * Oh boy. Work around https://github.com/scala-js/scala-js/issues/3435
  * For instance, every time we have this pattern:
  *
  * ```typescript
  * interface I {
  *   foo: I2;
  * }
  * interface I2 {
  *   (): void;
  * }
  * ```
  *
  * We expand the function call so it becomes a method
  * ````
  * interface I {
  *   foo(): void;
  * }
  * ````
  *
  * If not it wouldn't be safe to call from scala since it discards `this`.
  */
case class ExpandCallables(canExpand: (TsType, TsTreeScope) => Boolean) extends TransformClassMembers {
  override def newClassMembers(scope: TsTreeScope, x: HasClassMembers): Seq[TsMember] =
    x.members.flatMap {
      case m @ TsMemberProperty(cs, level, name, Some(tpe), None, isStatic, isReadonly, false)
          if canExpand(tpe, scope) =>
        ExpandCallables.callableTypes(scope)(tpe) match {
          case ExpandCallables.Expand(callables, keepOriginalMember) if callables.nonEmpty =>
            val keptOpt: Option[TsMemberProperty] =
              if (keepOriginalMember || !isReadonly) Some(m.copy(comments = m.comments + ExpandCallables.MarkerComment))
              else None

            val fs: Seq[TsMemberFunction] =
              callables.map {
                case (comments, sig) =>
                  val newCs = comments ++ cs
                  TsMemberFunction(newCs, level, name, sig, isStatic, isReadOnly = true, isOptional = false)
              }

            scope.logger.info(s"Expanded ${name.value} into ${fs.size} methods")

            fs ++ keptOpt
          case _ => m :: Nil
        }

      case other => other :: Nil
    }

}

object ExpandCallables {
  /* yeah, sorry. This is out of band information because we cannot
      rename the original member until we reach scala :/
   */
  val MarkerComment = Comment("/* Expanded */")

  sealed trait Result
  object Result {
    def combine(rs: Seq[Result]): Result = {
      val (expands, _) = rs.partitionCollect {
        case i: Expand => i
      }
      if (expands.nonEmpty) Expand(expands.flatMap(_.callables), expands.exists(_.keepOriginalMember))
      else Noop
    }
  }
  case class Expand(callables: Seq[(Comments, TsFunSig)], keepOriginalMember: Boolean) extends Result
  case object Noop extends Result

  def callableTypes(scope: TsTreeScope)(tpe: TsType): Result =
    tpe match {
      case x: TsTypeFunction  => Expand((NoComments, x.signature) :: Nil, keepOriginalMember = false)
      case x: TsTypeIntersect => Result.combine(x.types map callableTypes(scope))

      case x: TsTypeObject =>
        val (callables, rest) = x.members.partitionCollect { case TsMemberCall(cs, _, signature) => (cs, signature) }
        if (callables.nonEmpty) Expand(callables, rest.nonEmpty) else Noop

      case typeRef: TsTypeRef if !TsQIdent.Primitive(typeRef.name) =>
        scope
          .lookupTypeIncludeScope(typeRef.name)
          .collectFirst {
            case (_i: TsDeclInterface, newScope) =>
              val ms = AllMembersFor.forInterface(LoopDetector.initial, _i, newScope, typeRef.tparams)
              val (callables, rest) = ms.partitionCollect {
                case TsMemberCall(cs, _, signature) => (cs, signature)
              }
              if (callables.nonEmpty) Expand(callables, rest.nonEmpty) else Noop

            case (ta: TsDeclTypeAlias, newScope) =>
              callableTypes(newScope)(FillInTParams(ta, typeRef.tparams).alias)
          }
          .getOrElse(Noop)

      case _: TsTypeUnion       => Noop //todo: think about this
      case _: TsTypeConstructor => Noop //todo may want to do this later
      case _ => Noop
    }
}
