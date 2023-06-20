import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import cafe.adriel.voyager.transitions.SlideTransition
import features.databaseWeather
import ui.HomeScreen
import ui.WelcomeScreen
import java.awt.Dimension

@OptIn(ExperimentalAnimationApi::class)
fun main() = application {
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