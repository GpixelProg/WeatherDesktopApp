package data.api.model

data class ForecastHourly(
    val city: String,
    val longitude: Double,
    val latitude: Double,
    val timezone: String,
    val hourlyWeather: List<HourlyWeather>,
)
