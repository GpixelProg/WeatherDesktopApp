package implementation

import data.ItemInformant
import data.api.model.CurrentWeather
import data.api.model.DailyWeather
import repository.SourceConfiguratorRepository
import ui.theme.activeItemTextColor

open class SourceConfiguratorImpl(
    open val currentWeather: CurrentWeather,
    open val dailyWeather: DailyWeather? = null,
    override val feelsLike: ItemInformant = ItemInformant(
        value = currentWeather.feelsLike!!
    ),
    override val humidity: ItemInformant = ItemInformant(
        value = currentWeather.humidity!!
    ),
    override val pressure: ItemInformant = ItemInformant(
        value = currentWeather.pressure!!
    ),
    override val sunrise: ItemInformant = ItemInformant(
        value = dailyWeather!!.sunrise!!
    ),
    override val sunset: ItemInformant = ItemInformant(
        value = dailyWeather!!.sunset!!
    ),
    override val uvIndex: ItemInformant = ItemInformant(
        value = currentWeather.uvIndex!!
    ),
    override val windSpeed: ItemInformant = ItemInformant(
        value = currentWeather.windSpeed!!
    ),
    override val windDirectionFull: ItemInformant = ItemInformant(
        value = currentWeather.windDirectionFull!!
    ),
    override val rainfall: ItemInformant = ItemInformant(
        value = currentWeather.rainfall!!
    ),
    override val visibility: ItemInformant = ItemInformant(
        value = currentWeather.visibility!!
    ),
    override val airQualityIndex: ItemInformant = ItemInformant(
        value = currentWeather.airQualityIndex!!
    ),
) : SourceConfiguratorRepository