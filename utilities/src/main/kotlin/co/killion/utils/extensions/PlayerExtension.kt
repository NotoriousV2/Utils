package co.killion.utils.extensions

import co.killion.auctions.external.menu.Menu
import org.bukkit.entity.Player

fun Player.menu(rows: Int, block: Menu.() -> Unit): Menu {
    val menu = Menu(rows).apply(block)
    player.openInventory(menu.inventory)
    return menu
}

fun Player.sendColoredMessage(raw: String) {
    sendMessage(raw.colored)
}
