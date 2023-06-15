package data.api.model

import kotlinx.datetime.LocalTime

data class CurrentWeather(
    val city: String,
    val longitude: Double,
    val latitude: Double,
    val weatherDescription: String,
    val iconURL: String,

    val temperature: Double?,
    val feelsLike: Double?,
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
    val airQualityIndex: Int?,
)
