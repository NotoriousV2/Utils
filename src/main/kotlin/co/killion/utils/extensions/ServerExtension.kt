package co.killion.utils.extensions

import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.Server
import java.util.*

val server: Server
    get() = Bukkit.getServer()

fun Server.broadcastMessage(vararg parts: Any) {
    broadcastMessage(parts.joinToString(""))
}

fun Server.getExistingOfflinePlayer(name: String): OfflinePlayer? {
    return getOfflinePlayer(name).takeIf { it.isOnline || it.hasPlayedBefore() }
}

fun Server.getExistingOfflinePlayer(uuid: UUID): OfflinePlayer? {
    return getOfflinePlayer(uuid).takeIf { it.isOnline || it.hasPlayedBefore() }
}

fun Server.isPlayerOnline(uuid: UUID): Boolean {
    return getPlayer(uuid).takeIf { it !== null && it.isOnline } !== null
}