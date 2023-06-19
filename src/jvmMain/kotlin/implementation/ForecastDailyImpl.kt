package implementation

import data.MapCoordinates
import data.api.model.ForecastDaily
import features.databaseWeather
import features.weatherApi
import repository.ForecastDailyRepository

class ForecastDailyImpl : ForecastDailyRepository {
    override suspend fun getForecastDaily(coordinates: MapCoordinates): ForecastDaily {
        var forecastDaily: ForecastDaily? = null

        try {
            forecastDaily = weatherApi.getForecastDaily(MapCoordinates(coordinates.latitude, coordinates.longitude))
            databaseWeather.updateForecastDaily(MapCoordinates(coordinates.latitude, coordinates.longitude), forecastDaily)
        } catch (e: Exception) {
            forecastDaily = databaseWeather.getForecastDaily(MapCoordinates(coordinates.latitude, coordinates.longitude))
        }

        return forecastDaily!!
    }
}