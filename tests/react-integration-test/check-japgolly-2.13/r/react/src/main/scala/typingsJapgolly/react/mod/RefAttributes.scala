package typingsJapgolly.react.mod

import japgolly.scalajs.react.Callback
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.`|`
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait RefAttributes[T]
  extends StObject
     with Attributes {
  
  var ref: js.UndefOr[Ref[T]] = js.undefined
}
object RefAttributes {
  
  @scala.inline
  def apply[T](): RefAttributes[T] = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[RefAttributes[T]]
  }
  
  @scala.inline
  implicit class RefAttributesMutableBuilder[Self <: RefAttributes[_], T] (val x: Self with RefAttributes[T]) extends AnyVal {
    
    @scala.inline
    def setRef(value: Ref[T]): Self = StObject.set(x, "ref", value.asInstanceOf[js.Any])
    
    @scala.inline
    def setRefFunction1(value: /* instance */ T | Null => Callback): Self = StObject.set(x, "ref", js.Any.fromFunction1((t0: /* instance */ T | Null) => value(t0).runNow()))
    
    @scala.inline
    def setRefNull: Self = StObject.set(x, "ref", null)
    
    @scala.inline
    def setRefUndefined: Self = StObject.set(x, "ref", js.undefined)
  }
}
