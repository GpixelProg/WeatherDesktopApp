package screen_model

import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.navigator.Navigator
import features.databaseNotification
import ui.SetupFirstLocScreen

class SetupNotificationScreenModel(private val navigator: Navigator) : ScreenModel {
    var onNotification = mutableStateOf(databaseNotification.getNotificationStatus())
    var temperature = mutableStateOf(databaseNotification.getTemperature())

    fun switchOnNotification() {
        databaseNotification.updateNotificationStatus(onNotification.value)
    }

    fun onChangeTemperature() {
        databaseNotification.updateTemperature(temperature.value)
    }

    fun back() {
        navigator.pop()
    }

    fun next() {
        navigator.push(SetupFirstLocScreen)
    }
}