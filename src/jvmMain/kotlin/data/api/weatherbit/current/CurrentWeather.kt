package data.api.weatherbit.current

import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeather(
    val count: Int,
    val `data`: List<Data>
)