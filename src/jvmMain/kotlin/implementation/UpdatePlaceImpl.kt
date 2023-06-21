package implementation

import data.MapCoordinates
import features.databaseWeather
import features.weatherApi
import javafx.application.Application
import kotlinx.coroutines.*
import repository.UpdatePlaceRepository

class UpdatePlaceImpl : UpdatePlaceRepository {
    override suspend fun updatePlace(oldCoordinates: MapCoordinates, newCoordinates: MapCoordinates) {
        coroutineScope {
            launch {
                launch {
                    println("updatePlaceCurr: $oldCoordinates -> $newCoordinates")
                    val currentWeather = weatherApi.getCurrentWeather(newCoordinates)
                    databaseWeather.updateCurrentWeather(oldCoordinates, currentWeather)
                }
                launch {
                    println("updatePlace: $oldCoordinates -> $newCoordinates")
                    val hourlyForecast = weatherApi.getForecastHourly(newCoordinates)
                    databaseWeather.updateForecastHourly(oldCoordinates, hourlyForecast)
                }
                launch {
                    println("updatePlace: $oldCoordinates -> $newCoordinates")
                    val dailyForecast = weatherApi.getForecastDaily(newCoordinates)
                    databaseWeather.updateForecastDaily(oldCoordinates, dailyForecast)
                }
            }.join()
        }
    }
}