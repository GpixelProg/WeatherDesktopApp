package data.api.model

data class AlertWeather(
    val city: String,
    val longitude: Double,
    val latitude: Double,

    val alertList: List<Alert>
)
