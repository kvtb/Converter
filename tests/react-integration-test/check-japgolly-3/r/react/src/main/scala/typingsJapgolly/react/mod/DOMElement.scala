package typingsJapgolly.react.mod

import japgolly.scalajs.react.Callback
import org.scalajs.dom.Element
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

// string fallback for custom web-components
trait DOMElement[P /* <: HTMLAttributes[T] | SVGAttributes[T] */, T /* <: Element */]
  extends StObject
     with ReactElement {
  
  var ref: LegacyRef[T]
}
object DOMElement {
  
  inline def apply[P /* <: HTMLAttributes[T] | SVGAttributes[T] */, T /* <: Element */](props: Any, `type`: Any): DOMElement[P, T] = {
    val __obj = js.Dynamic.literal(props = props.asInstanceOf[js.Any], key = null, ref = null)
    __obj.updateDynamic("type")(`type`.asInstanceOf[js.Any])
    __obj.asInstanceOf[DOMElement[P, T]]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: DOMElement[?, ?], P /* <: HTMLAttributes[T] | SVGAttributes[T] */, T /* <: Element */] (val x: Self & (DOMElement[P, T])) extends AnyVal {
    
    inline def setRef(value: LegacyRef[T]): Self = StObject.set(x, "ref", value.asInstanceOf[js.Any])
    
    inline def setRefFunction1(value: /* instance */ T | Null => Callback): Self = StObject.set(x, "ref", js.Any.fromFunction1((t0: /* instance */ T | Null) => value(t0).runNow()))
    
    inline def setRefNull: Self = StObject.set(x, "ref", null)
  }
}
