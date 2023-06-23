package ui.components

import kotlinx.datetime.LocalTime

fun LocalTime.toSting(): String {
    return "${if (this.hour < 10) "0" else ""}${this.hour}:${if (this.minute < 10) "0" else ""}${this.minute}"
}