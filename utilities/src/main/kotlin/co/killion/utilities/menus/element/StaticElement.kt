package co.killion.utilities.menus.element

import co.killion.utilities.extensions.cancel
import co.killion.utilities.menus.ElementContainer
import co.killion.utilities.menus.Vector2i
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

class StaticElement(position: Vector2i, size: Vector2i, parent: ElementContainer) : Element(position, size, parent) {

    var icon = ItemStack(Material.STONE)
    var clickCallback: (position: Vector2i, event: InventoryClickEvent) -> Unit = { _, _ -> }

    inline fun icon(base: ItemStack = icon, body: ItemStack.() -> Unit) {
        icon = base.apply(body)
    }

    fun onClick(clickCallback: (position: Vector2i) -> Unit) {
        this.clickCallback = { it, _ -> clickCallback(it) }
    }

    fun onAdvancedClick(clickCallback: (position: Vector2i, event: InventoryClickEvent) -> Unit) {
        this.clickCallback = clickCallback
    }

    override fun onClick(event: InventoryClickEvent, player: Player, position: Vector2i) {
        clickCallback(position, event)
        event.cancel()
    }

    override fun setup() {
        for(pos in section) {
            section[pos] = icon
        }
    }

    companion object : Type<StaticElement> {
        override fun create(position: Vector2i, size: Vector2i, parent: ElementContainer): StaticElement {
            return StaticElement(position, size, parent)
        }
    }

}