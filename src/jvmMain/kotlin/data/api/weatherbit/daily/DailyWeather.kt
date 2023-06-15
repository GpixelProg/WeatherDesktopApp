package data.api.weatherbit.daily


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyWeather(
    @SerialName("city_name")
    val cityName: String,
    @SerialName("country_code")
    val countryCode: String,
    @SerialName("data")
    val `data`: List<Data?>?,
    @SerialName("lat")
    val lat: Double,
    @SerialName("lon")
    val lon: Double,
    @SerialName("state_code")
    val stateCode: String,
    @SerialName("timezone")
    val timezone: String
)