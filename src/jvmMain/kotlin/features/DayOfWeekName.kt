package features

import kotlinx.datetime.LocalDate
import kotlinx.datetime.daysUntil

fun LocalDate.getDayOfWeekName(): String {
    val dayOfWeekNames = listOf(
        "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    )
    val baseDate = LocalDate(2000, 1, 3) // A Monday for reference
    val days = baseDate.daysUntil(this)
    val dayOfWeekIndex = (days % 7).toInt()
    return dayOfWeekNames[dayOfWeekIndex]
}