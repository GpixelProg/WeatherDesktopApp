package implementation

import data.MapCoordinates
import data.api.model.ForecastHourly
import features.databaseWeather
import features.weatherApi
import repository.ForecastHourlyRepository

class ForecastHourlyImpl : ForecastHourlyRepository {
    override suspend fun getForecastHourly(coordinates: MapCoordinates): ForecastHourly {
        var forecastHourly: ForecastHourly? = null

        try {
            forecastHourly = weatherApi.getForecastHourly(MapCoordinates(coordinates.latitude, coordinates.longitude))
            databaseWeather.updateForecastHourly(MapCoordinates(coordinates.latitude, coordinates.longitude), forecastHourly)
        } catch (e: Exception) {
            forecastHourly = databaseWeather.getForecastHourly(MapCoordinates(coordinates.latitude, coordinates.longitude))
        }

        return forecastHourly!!
    }
}