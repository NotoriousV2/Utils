package co.killion.utils.extensions

import com.destroystokyo.paper.profile.ProfileProperty
import org.apache.commons.lang.WordUtils
import org.bukkit.Bukkit
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.SkullMeta
import java.util.*

fun ItemMeta.displayName(vararg parts: Any?) {
    displayName = parts.joinToString("")
}

fun ItemMeta.appendLore(vararg parts: Any?) {
    val newLore = (lore as? MutableList<String> ?: lore?.toMutableList() ?: ArrayList())
    newLore.addAll(parts.joinToString("").split("\n"))
    lore = newLore
}

fun ItemMeta.appendError(error: String) {
    val newLore = (lore as? MutableList<String> ?: lore?.toMutableList() ?: ArrayList())
    val del = "\n&r&c".colored
    newLore.addAll(WordUtils.wrap(del + error, 26, del, false).split("\n"))
    lore = newLore
}

fun ItemMeta.appendDescription(desc: String) {
    val newLore = (lore as? MutableList<String> ?: lore?.toMutableList() ?: ArrayList())
    val del = "\n&r&7".colored
    newLore.addAll(WordUtils.wrap(del + desc, 26, del, false).split("\n"))
    lore = newLore
}

fun ItemMeta.enchantedEffect() {
    if(hasEnchants())
        return

    addEnchant(Enchantment.LURE, 1, true)
    addItemFlags(ItemFlag.HIDE_ENCHANTS)
}

fun ItemMeta.createHeadByTextures(name: String, id: String, textures: String) {
    val profile = Bukkit.createProfile(UUID.fromString(id), name)
    val properties = profile.properties
    properties.add(ProfileProperty("textures", textures))
    (this as SkullMeta).playerProfile = profile
}