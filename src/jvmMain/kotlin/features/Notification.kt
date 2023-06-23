package features


import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Notification
import androidx.compose.ui.window.TrayState
import androidx.compose.ui.window.rememberNotification
import data.api.model.CurrentWeather

lateinit var trayState: TrayState

@Composable
fun NotificationSender(currentWeatherList: List<CurrentWeather>) {
    if (currentWeatherList.isNotEmpty() && databaseNotification.getNotificationStatus()) {
        currentWeatherList.forEach { currentWeather ->
            if (currentWeather.temperature!!.toInt() >= databaseNotification.getTemperature()) {
                val notification = rememberNotification(
                    title = "Meteo: Temperature rise",
                    message = "In ${currentWeather.city} the temperature is above normal: ${currentWeather.temperature}°C"
                )
                trayState.sendNotification(notification)
            }
        }
    }
}