package co.killion.utilities.extensions

import org.bukkit.event.Cancellable

fun Cancellable.cancel() {
    isCancelled = true
}

fun Cancellable.uncancel() {
    isCancelled = false
}