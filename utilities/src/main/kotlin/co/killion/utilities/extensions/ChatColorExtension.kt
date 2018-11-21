package co.killion.utilities.extensions

import org.bukkit.ChatColor


fun color(string: String): String = ChatColor.translateAlternateColorCodes('&', string)

fun coloredListOf(vararg strings: String) = strings.map(::color)

val String.colored get() = color(this)

val Iterable<String>.colored get() = map(::color)