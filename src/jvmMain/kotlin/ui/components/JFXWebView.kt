package ui.components

import data.MapCoordinates
import javafx.application.Platform
import javafx.embed.swing.JFXPanel
import javafx.event.Event
import javafx.scene.Scene
import javafx.scene.web.WebView

class JFXWebView(private val onClick: (MapCoordinates) -> Unit, private val mapCoordinates: MapCoordinates? = null) : JFXPanel() {
    init {
        Platform.runLater(::initialiseJavaFXScene)
    }

    private fun initialiseJavaFXScene() {
        val webView = WebView()
        val webEngine = webView.engine

        // Налаштувати обробник JavaScript
        webView.setOnMouseClicked { event: Event ->
            val latitude = webView.engine.executeScript("latitude") as Double?
            val longitude = webView.engine.executeScript("longitude") as Double?

//            println("Latitude: $latitude | Longitude: $longitude")

            if (latitude != null && longitude != null) {
                onClick(MapCoordinates(latitude, longitude))
            }
        }

        val coordinates = if (mapCoordinates != null)
            "?latitude=${mapCoordinates.latitude}&longitude=${mapCoordinates.longitude}"
        else ""

        println("https://gpixelprog.github.io/WebMap/$coordinates")

        webEngine.load("https://gpixelprog.github.io/WebMap/$coordinates")
        val scene = Scene(webView)
        setScene(scene)
    }
}