package co.killion.utils.extensions

import org.bukkit.event.Cancellable

fun Cancellable.cancel() {
    isCancelled = true
}

fun Cancellable.uncancel() {
    isCancelled = false
}