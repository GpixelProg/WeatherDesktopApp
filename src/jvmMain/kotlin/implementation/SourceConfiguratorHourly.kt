package implementation

import data.ItemInformant
import data.api.model.CurrentWeather
import data.api.model.DailyWeather
import data.api.model.HourlyWeather
import ui.theme.activeItemTextColor

class SourceConfiguratorHourly(
    override val currentWeather: CurrentWeather,
    override val dailyWeather: DailyWeather,
    private val hourlyWeather: HourlyWeather
) : SourceConfiguratorImpl(currentWeather, dailyWeather) {

    override val feelsLike = ItemInformant(
        activeColor = activeItemTextColor,
        value = hourlyWeather.feelsLike!!,
    )

    override val humidity = ItemInformant(
        activeColor = activeItemTextColor,
        value = hourlyWeather.humidity!!,
    )

    override val pressure = ItemInformant(
        activeColor = activeItemTextColor,
        value = hourlyWeather.pressure!!,
    )

    override val uvIndex = ItemInformant(
        activeColor = activeItemTextColor,
        value = hourlyWeather.uvIndex!!,
    )

    override val windSpeed = ItemInformant(
        activeColor = activeItemTextColor,
        value = hourlyWeather.windSpeed!!,
    )

    override val windDirectionFull = ItemInformant(
        activeColor = activeItemTextColor,
        value = hourlyWeather.windDirectionFull!!,
    )

    override val rainfall = ItemInformant(
        activeColor = activeItemTextColor,
        value = hourlyWeather.rainfall!!,
    )

    override val visibility = ItemInformant(
        activeColor = activeItemTextColor,
        value = hourlyWeather.visibility!!,
    )
}