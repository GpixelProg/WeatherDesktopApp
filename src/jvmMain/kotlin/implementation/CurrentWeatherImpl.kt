package implementation

import data.MapCoordinates
import data.api.model.CurrentWeather
import repository.CurrentWeatherRepository
import repository.WeatherApiRepository

class CurrentWeatherImpl : CurrentWeatherRepository {

    private val weatherApi: WeatherApiRepository = WeatherbitApiImpl()
    override suspend fun getCurrentWeather(coordinates: MapCoordinates): CurrentWeather {
        lateinit var currentWeather: CurrentWeather

        try {
            val currentWeather = weatherApi.getCurrentWeather(MapCoordinates(coordinates.latitude, coordinates.longitude))
        } catch (e: Exception) {

            println(e)
        }

        return currentWeather
    }
}