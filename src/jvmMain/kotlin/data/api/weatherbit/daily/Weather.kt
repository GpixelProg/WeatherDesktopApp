package data.api.weatherbit.daily


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    @SerialName("code")
    val code: Int,
    @SerialName("description")
    val description: String,
    @SerialName("icon")
    val icon: String
)