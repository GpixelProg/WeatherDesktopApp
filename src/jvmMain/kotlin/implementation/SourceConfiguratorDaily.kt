package implementation

import data.ItemInformant
import data.api.model.CurrentWeather
import data.api.model.DailyWeather
import ui.theme.activeItemTextColor

class SourceConfiguratorDaily(
    override val currentWeather: CurrentWeather,
    override val dailyWeather: DailyWeather
) : SourceConfiguratorImpl(currentWeather, dailyWeather) {

        override val humidity = ItemInformant(
            activeColor = activeItemTextColor,
            value = dailyWeather.humidity!!,
        )

        override val pressure = ItemInformant(
            activeColor = activeItemTextColor,
            value = dailyWeather.pressure!!,
        )

        override val sunrise = ItemInformant(
            activeColor = activeItemTextColor,
            value = dailyWeather.sunrise!!,
        )

        override val sunset = ItemInformant(
            activeColor = activeItemTextColor,
            value = dailyWeather.sunset!!,
        )

        override val uvIndex = ItemInformant(
            activeColor = activeItemTextColor,
            value = dailyWeather.uvIndex!!,
        )

        override val windSpeed = ItemInformant(
            activeColor = activeItemTextColor,
            value = dailyWeather.windSpeed!!,
        )

        override val windDirectionFull = ItemInformant(
            activeColor = activeItemTextColor,
            value = dailyWeather.windDirectionFull!!,
        )

        override val rainfall = ItemInformant(
            activeColor = activeItemTextColor,
            value = dailyWeather.rainfall!!,
        )

        override val visibility = ItemInformant(
            activeColor = activeItemTextColor,
            value = dailyWeather.visibility!!,
        )
}