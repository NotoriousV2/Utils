package co.killion.utilities.menus

import co.killion.utilities.extensions.cancel
import co.killion.utilities.menus.element.Element
import co.killion.utilities.menus.element.StaticElement
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

abstract class ElementContainer(val contentSize: Vector2i) {
    protected val elements = LinkedHashSet<Element>()
    abstract val section: InventorySection

    operator fun get(position: Vector2i): Element? {
        return elements.find { position in it }
    }

    open fun add(element: Element): Boolean {
        //TODO: Check for intersections?
        if (!elements.add(element))
            return false
        setup(element)
        return true
    }

    fun remove(element: Element): Boolean {
        return elements.remove(element)
    }

    fun isVisible(element: Element): Boolean {
        return element in elements
    }

    inline fun <T : Element> element(
        type: Element.Type<T>,
        position: Vector2i,
        size: Vector2i = 1 x 1,
        body: T.() -> Unit = {}
    ): T {
//		println("adding element $position (slot ${section.getAbsoluteSlot(position)}) to container $this")
        val element = type.create(position, size, this).apply(body)
        add(element)
        return element
    }

    inline fun item(
        position: Vector2i,
        icon: ItemStack = ItemStack(Material.STONE),
        size: Vector2i = 1 x 1,
        body: StaticElement.() -> Unit = {}
    ) {
        element(StaticElement, position, size) {
            this.icon = icon
            body()
        }
    }

    inline fun item(
        position: Vector2i,
        icon: Material,
        size: Vector2i = 1 x 1,
        body: StaticElement.() -> Unit = {}
    ) {
        item(
                position,
                ItemStack(icon),
                size = size,
                body = body
        )
    }

    open fun onClick(
        event: InventoryClickEvent,
        player: Player,
        position: Vector2i
    ) {
        val element = get(position) ?: return event.cancel()
        element.onClick(event, player, position - element.position)
    }

    private fun setup(element: Element) {
        element.setup()
    }

    infix fun Int.x(y: Int) = Vector2i(this, y)
}