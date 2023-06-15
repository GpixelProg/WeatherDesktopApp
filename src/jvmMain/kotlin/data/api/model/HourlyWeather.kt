package data.api.model


import kotlinx.datetime.LocalTime

data class HourlyWeather(
    val timestampLocal: LocalTime,
    val description: String,
    val iconURL: String,

    val temperature: Double?,
    val feelsLike: Double?,
    val pressure: Double?,
    val humidity: Double?,
    val uvIndex: Int?,
    val windSpeed: Double?,
    val windDirectionShort: String?,
    val windDirectionFull: String?,
    val rainfall: Double?,
    val visibility: Double?,
)
