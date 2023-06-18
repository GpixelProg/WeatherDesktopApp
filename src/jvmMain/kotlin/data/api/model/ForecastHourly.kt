package data.api.model

import kotlinx.datetime.LocalDateTime

data class ForecastHourly(
    val lastUpdate: LocalDateTime,
    val city: String,
    val longitude: Double,
    val latitude: Double,
    val timezone: String,
    val hourlyWeather: List<HourlyWeather>,
)
