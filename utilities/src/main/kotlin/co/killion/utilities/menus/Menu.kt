package co.killion.utilities.menus

import co.killion.utilities.extensions.server
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder

class Menu(rows: Int) : ElementContainer(Vector2i(9, rows)), InventoryHolder {
    private var _inventory = createInventory()
    private var closeCallback: (event: InventoryCloseEvent) -> Unit = {}

    override val section: InventorySection
        get() = InventorySection(_inventory, Vector2i(0, 0), contentSize)

    var title: String? = null
        set(value) {
            field = value
            // TODO: This could cause problems if the menu was already open.
            _inventory = createInventory()
        }

    private fun createInventory(): Inventory {
        val slotCount = contentSize.x * contentSize.y
        return server.createInventory(this, slotCount, title ?: "!!!")
    }

    fun title(title: String) {
        this.title = title
    }

    fun onClose(body: (event: InventoryCloseEvent) -> Unit) {
        closeCallback = body
    }

    fun onClose(event: InventoryCloseEvent) {
        closeCallback(event)
    }

    override fun getInventory() = _inventory
}