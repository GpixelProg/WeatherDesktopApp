package implementation

import data.MapCoordinates
import features.databaseWeather
import repository.DeletePlaceRepository

class DeletePlaceImpl : DeletePlaceRepository {
    override suspend fun delete(coordinates: MapCoordinates) {
        databaseWeather.deleteWeatherData(coordinates)
    }
}