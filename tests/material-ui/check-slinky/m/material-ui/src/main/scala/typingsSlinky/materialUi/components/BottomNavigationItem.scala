package typingsSlinky.materialUi.components

import typingsSlinky.materialUi.MaterialUI.BottomNavigation.BottomNavigationItemProps
import typingsSlinky.materialUi.bottomNavigationItemMod.default
import scala.scalajs.js
import scala.scalajs.js.`|`
import scala.scalajs.js.annotation._

object BottomNavigationItem {
  @JSImport("material-ui/BottomNavigation/BottomNavigationItem", JSImport.Default)
  @js.native
  object component extends js.Object
  
  def withProps(p: BottomNavigationItemProps): SharedBuilder_BottomNavigationItemProps_1638895878[default] = new SharedBuilder_BottomNavigationItemProps_1638895878[default](js.Array(this.component, p.asInstanceOf[js.Any]))
  implicit def make(companion: BottomNavigationItem.type): SharedBuilder_BottomNavigationItemProps_1638895878[default] = new SharedBuilder_BottomNavigationItemProps_1638895878[default](js.Array(this.component, js.Dictionary.empty))()
}

