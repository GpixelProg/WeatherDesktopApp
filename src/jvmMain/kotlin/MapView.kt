import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import javafx.embed.swing.JFXPanel
import javafx.event.Event
import javafx.scene.Scene
import javafx.scene.web.WebView
import javax.swing.SwingUtilities

@Composable
fun MapView() {
    val jfxPanel = JFXPanel()

    SwingUtilities.invokeLater {
        val webView = WebView()
        webView.engine.load("https://gpixelprog.github.io/WebMap/")

        webView.setOnMouseClicked { event: Event ->
            println("Mouse clicked")
        }

        webView.engine.setOnStatusChanged { event ->

            val latitude = webView.engine.executeScript("markerLatLng.lat") as Double
            val longitude = webView.engine.executeScript("markerLatLng.lng") as Double

            println("Latitude: $latitude | Longitude: $longitude")
        }

        val scene = Scene(webView)
        jfxPanel.scene = scene
    }

}