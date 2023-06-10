import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import androidx.compose.ui.node.Ref
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import javafx.application.Platform
import javafx.embed.swing.JFXPanel
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.StackPane
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.swing.SwingUtilities

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        val container = window

        var dataLocation by remember { mutableStateOf("") }

        Column {

            Text("Data location: $dataLocation")

            Map(
                onClick = { coordinates ->
                    dataLocation = "Latitude: ${coordinates.latitude} | Longitude: ${coordinates.longitude}"
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            )
        }

        println("Dev mode: TRUE")
    }
}

@Composable
fun Map(
    onClick: (ObjectCoordinates) -> Unit,
    modifier: Modifier,
) {
    val jfxWebViewRef = remember { Ref<JFXWebView>() }
    var webView by remember { mutableStateOf<JFXWebView?>(null) }

    DisposableEffect(Unit) {
        val jfxWebView = JFXWebView(onClick)
        jfxWebViewRef.value = jfxWebView

        onDispose {
            // Закриття ресурсів
            jfxWebViewRef.value = null
        }
    }

    LaunchedEffect(jfxWebViewRef.value) {
        val jfxWebView = jfxWebViewRef.value
        jfxWebView?.let { webView_ ->
            withContext(Dispatchers.Default) {
                SwingUtilities.invokeLater {
                    webView = webView_
                }
            }
        }
    }

    AnimatedVisibility(
        visible = webView != null,
        enter = fadeIn(animationSpec = tween(durationMillis = 500)),
        exit = fadeOut(animationSpec = tween(durationMillis = 500))
    ) {
        // Insert JFXWebView into SwingPanel
        SwingPanel(
            factory = { webView!! },
            modifier = modifier,
        )
    }
}




