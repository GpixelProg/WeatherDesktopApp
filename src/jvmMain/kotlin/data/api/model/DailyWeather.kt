package data.api.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

data class DailyWeather(
    val datestampLocal: LocalDate,
    val description: String,
    val iconURL: String,

    val temperature: Double?,
    val maxTemperature: Double?,
    val minTemperature: Double?,
    val pressure: Double?,
    val humidity: Double?,
    val sunrise: LocalTime?,
    val sunset: LocalTime?,
    val uvIndex: Int?,
    val windSpeed: Double?,
    val windDirectionShort: String?,
    val windDirectionFull: String?,
    val rainfall: Double?,
    val visibility: Double?,
)
