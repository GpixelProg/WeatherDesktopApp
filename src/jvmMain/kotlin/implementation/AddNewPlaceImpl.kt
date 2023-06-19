package implementation

import data.MapCoordinates
import features.databaseWeather
import features.weatherApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import repository.AddNewPlaceRepository

class AddNewPlaceImpl : AddNewPlaceRepository {
    override fun addNewPlace(coordinates: MapCoordinates) {
        CoroutineScope(Job() + Dispatchers.Default).launch {
            val currentWeather = weatherApi.getCurrentWeather(coordinates)
            databaseWeather.updateCurrentWeather(coordinates, currentWeather)
        }
    }
}