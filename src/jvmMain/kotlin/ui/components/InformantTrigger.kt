package ui.components

import androidx.compose.runtime.Composable
import data.SourceData
import data.TypeSource
import data.api.model.CurrentWeather
import data.api.model.ForecastDaily
import data.api.model.ForecastHourly
import implementation.SourceConfiguratorDaily
import implementation.SourceConfiguratorHourly
import implementation.SourceConfiguratorImpl
import repository.SourceConfiguratorRepository

@Composable
fun InformantTrigger(
    currentWeather: CurrentWeather,
    forecastHourly: ForecastHourly,
    forecastDaily: ForecastDaily,
    sourceData: SourceData,
) : SourceConfiguratorRepository {
    val sourceConfigurator: SourceConfiguratorRepository

    when(sourceData.typeSource) {
        TypeSource.DEFAULT -> {
            sourceConfigurator = SourceConfiguratorImpl(currentWeather, forecastDaily.dailyWeather[0])
        }

        TypeSource.HOURLY -> {
            sourceConfigurator = SourceConfiguratorHourly(currentWeather, forecastDaily.dailyWeather[0], forecastHourly.hourlyWeather[sourceData.index!!])
        }

        TypeSource.DAILY -> {
            sourceConfigurator = SourceConfiguratorDaily(currentWeather, forecastDaily.dailyWeather[sourceData.index!!])
        }

        else -> {
            sourceConfigurator = SourceConfiguratorImpl(currentWeather)
        }
    }
    return sourceConfigurator
}