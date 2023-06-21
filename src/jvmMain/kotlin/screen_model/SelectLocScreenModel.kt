package screen_model

import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import cafe.adriel.voyager.navigator.Navigator
import data.MapCoordinates
import features.addNewPlace
import features.currentWeather
import features.updatePlace
import kotlinx.coroutines.launch

class SelectLocScreenModel(private val navigator: Navigator) : ScreenModel {

    var mapCoordinates = mutableStateOf<MapCoordinates?>(null)

    fun back() {
        navigator.pop()
    }

    fun addNewLocation() {
        if (mapCoordinates.value != null) {
            coroutineScope.launch {
                addNewPlace.addNewPlace(mapCoordinates.value!!)
                navigator.pop()
            }
        }
    }

    fun updateLocation(oldCoordinates: MapCoordinates) {
        if (mapCoordinates.value != null) {
            coroutineScope.launch {
                updatePlace.updatePlace(oldCoordinates = oldCoordinates, newCoordinates = mapCoordinates.value!!)
                navigator.pop()
            }
        }
    }
}