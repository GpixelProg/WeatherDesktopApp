package implementation

import data.MapCoordinates
import features.databaseWeather
import features.weatherApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import repository.AddNewPlaceRepository

class AddNewPlaceImpl : AddNewPlaceRepository {
    override suspend fun addNewPlace(coordinates: MapCoordinates) {
        coroutineScope {
            launch {
                val currentWeather = weatherApi.getCurrentWeather(coordinates)
                databaseWeather.updateCurrentWeather(coordinates, currentWeather)
            }.join()
        }
    }
}