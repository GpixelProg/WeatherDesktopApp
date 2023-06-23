package data.api.weatherbit.current

import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    val code: Int,
    val description: String,
    val icon: String
)