import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import javafx.application.Platform
import javafx.concurrent.Worker
import javafx.embed.swing.JFXPanel
import javafx.event.Event
import javafx.scene.Scene
import javafx.scene.web.WebView

/**
 * From, https://stackoverflow.com/a/26028556
 */
class JFXWebView(private val onClick: (ObjectCoordinates) -> Unit) : JFXPanel() {
    init {
        Platform.runLater(::initialiseJavaFXScene)
    }

    fun initialiseJavaFXScene() {
        val webView = WebView()
        val webEngine = webView.engine

        // Налаштувати обробник JavaScript
        webView.setOnMouseClicked { event: Event ->
            val latitude = webView.engine.executeScript("latitude") as Double?
            val longitude = webView.engine.executeScript("longitude") as Double?

//            println("Latitude: $latitude | Longitude: $longitude")

            if (latitude != null && longitude != null) {
                onClick(ObjectCoordinates(latitude, longitude))
            }
        }

        webEngine.load("https://gpixelprog.github.io/WebMap/")
        val scene = Scene(webView)
        setScene(scene)
    }
}

data class ObjectCoordinates(
    val latitude: Double,
    val longitude: Double
)