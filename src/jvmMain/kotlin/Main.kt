import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.*
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import cafe.adriel.voyager.transitions.SlideTransition
import features.databaseWeather
import features.trayState
import ui.HomeScreen
import ui.WelcomeScreen
import java.awt.Dimension

@OptIn(ExperimentalAnimationApi::class)
fun main() = application {

    val trayState_ = rememberTrayState()
    trayState = trayState_

    val notification = rememberNotification(
        title = "Meteo",
        message = "Meteo is running in the background"
    )

    Tray(
        state = trayState_,
        icon = painterResource("weather.png"),
        menu = {
            Item(
                text = "Exit",
                onClick = ::exitApplication
            )

            Item(
                text = "Show",
                onClick = {
                    trayState_.sendNotification(notification)
                }
            )
        }
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = "Meteo",
    ) {
        window.minimumSize = Dimension(1200, 800)

        if (databaseWeather.getAllCurrentWeather().isEmpty()) {
            Navigator(WelcomeScreen) {
                SlideTransition(it)
            }
        } else {
            Navigator(HomeScreen) {
                FadeTransition(it)
            }
        }
    }
}