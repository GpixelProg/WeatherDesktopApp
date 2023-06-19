package implementation

import data.MapCoordinates
import data.api.model.CurrentWeather
import features.databaseWeather
import features.weatherApi
import repository.CurrentWeatherRepository
import repository.DatabaseWeatherRepository
import repository.WeatherApiRepository

class CurrentWeatherImpl : CurrentWeatherRepository {
    override suspend fun getCurrentWeather(coordinates: MapCoordinates): CurrentWeather {
        var currentWeather: CurrentWeather? = null

        try {
            currentWeather = weatherApi.getCurrentWeather(MapCoordinates(coordinates.latitude, coordinates.longitude))
            databaseWeather.updateCurrentWeather(MapCoordinates(coordinates.latitude, coordinates.longitude), currentWeather)
        } catch (e: Exception) {
            currentWeather = databaseWeather.getCurrentWeather(MapCoordinates(coordinates.latitude, coordinates.longitude))
        }

        return currentWeather!!
    }

    override suspend fun getAllCurrentWeather(): List<CurrentWeather> {
        var currentWeather: MutableList<CurrentWeather> = mutableListOf()

        val currentWeatherDBList = databaseWeather.getAllCurrentWeather()

        try {
            currentWeatherDBList.forEach {
                currentWeather.add(weatherApi.getCurrentWeather(MapCoordinates(it.latitude, it.longitude)))
            }
        } catch (e: Exception) {
            currentWeather = currentWeatherDBList as MutableList<CurrentWeather>
        }

        return currentWeather
    }
}