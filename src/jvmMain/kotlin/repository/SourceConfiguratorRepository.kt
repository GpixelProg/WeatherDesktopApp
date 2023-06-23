package repository

import data.ItemInformant
import data.api.model.CurrentWeather
import kotlinx.datetime.LocalTime

interface SourceConfiguratorRepository {
    val feelsLike: ItemInformant
    val humidity: ItemInformant
    val pressure: ItemInformant
    val sunrise: ItemInformant
    val sunset: ItemInformant
    val uvIndex: ItemInformant
    val windSpeed: ItemInformant
    val windDirectionFull: ItemInformant
    val rainfall: ItemInformant
    val visibility: ItemInformant
    val airQualityIndex: ItemInformant
}