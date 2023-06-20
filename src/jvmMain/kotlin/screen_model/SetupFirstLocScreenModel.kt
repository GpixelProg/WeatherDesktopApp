package screen_model

import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.navigator.Navigator
import data.MapCoordinates
import features.addNewPlace
import ui.HomeScreen

class SetupFirstLocScreenModel(private val navigator: Navigator) : ScreenModel {
    var coordinates = mutableStateOf<MapCoordinates?>(null)

    fun back() {
        navigator.pop()
    }

    fun next() {
        if (coordinates.value != null) {
            addNewPlace.addNewPlace(coordinates.value!!)
            navigator.push(HomeScreen)
        }
    }
}