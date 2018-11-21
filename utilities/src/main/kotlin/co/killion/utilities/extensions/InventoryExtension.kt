package co.killion.utilities.extensions

import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

val border = ItemStack(Material.ORANGE_STAINED_GLASS_PANE).apply { editMeta { displayName(" ") } }

val Inventory.isFull
    get() = this.firstEmpty() == -1

fun Inventory.fillEmptySlots(filler: ItemStack) = this.contents.forEachIndexed { i, item ->
    if (item == null) this.setItem(i, filler)
}

fun Inventory.fillBorders(item: ItemStack = border) {
    val size = this.size
    val rows = size / 9

    if (rows >= 3) {
        for (i in 0..8) {
            this.setItem(i, item)
        }

        var s = 8
        while (s < this.size - 9) {
            val lastSlot = s + 1
            this.setItem(s, item)
            this.setItem(lastSlot, item)
            s += 9
        }

        for (lr in this.size - 9 until this.size) {
            this.setItem(lr, item)
        }
    }
}

fun Inventory.fill(start: Int, end: Int, item: ItemStack = border) {
    for (i in start..end) {
        this.setItem(i, item)
    }
}

fun Inventory.fillRow(row: Int, item: ItemStack = border) {
    val start = row * 9
    for (i in start..start+8) {
        this.setItem(i, item)
    }
}