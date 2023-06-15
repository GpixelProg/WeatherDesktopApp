package data.api.model

data class ForecastDaily(
    val city: String,
    val longitude: Double,
    val latitude: Double,
    val timezone: String,
    val dailyWeather: List<DailyWeather>,
)
