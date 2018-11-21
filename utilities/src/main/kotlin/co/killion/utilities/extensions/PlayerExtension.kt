package co.killion.utilities.extensions

import co.killion.utilities.menus.Menu
import org.bukkit.entity.Player

fun Player.menu(rows: Int, block: Menu.() -> Unit): Menu {
    val menu = Menu(rows).apply(block)
    player.openInventory(menu.inventory)
    return menu
}

fun Player.sendColoredMessage(raw: String) {
    sendMessage(raw.colored)
}
