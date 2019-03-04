package typings
package rxjsLib.internalObservableMod

import scala.scalajs.js
import scala.scalajs.js.`|`
import scala.scalajs.js.annotation._

@JSImport("rxjs/internal/Observable", "Observable")
@js.native
class Observable[T] ()
  extends rxjsLib.internalTypesMod.Subscribable[T] {
  /** Internal implementation detail, do not use directly. */
  var _isScalar: scala.Boolean = js.native
  @JSName("foo")
  var foo_Original: rxjsLib.internalTypesMod.OperatorFunction[scala.Double, java.lang.String] = js.native
  /** @deprecated This is an internal implementation detail, do not use. */
  var source: Observable[_] = js.native
  def foo(source: Observable[scala.Double]): Observable[java.lang.String] = js.native
  def pipe(): Observable[T] = js.native
}

