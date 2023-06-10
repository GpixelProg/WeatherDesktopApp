import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import javafx.embed.swing.JFXPanel
import javafx.scene.Scene
import javafx.scene.layout.StackPane
import javafx.scene.web.WebEngine
import javafx.scene.web.WebView

@Composable
fun GoogleMaps() {
    val jfxPanel = remember { JFXPanel() }

    val webView = WebView()
    val webEngine: WebEngine = webView.engine

    // Завантажте сторінку Google Maps у WebView
    webEngine.load("https://www.google.com/maps")

    val jfxScene = Scene(StackPane(webView))
    jfxPanel.scene = jfxScene


}