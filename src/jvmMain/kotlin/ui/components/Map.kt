package ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import androidx.compose.ui.node.Ref
import data.MapCoordinates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.swing.SwingUtilities

@Composable
fun Map(
    onClick: (MapCoordinates) -> Unit,
    coordinates: MapCoordinates? = null,
    modifier: Modifier,
) {
    val jfxWebViewRef = remember { Ref<JFXWebView>() }
    var webView by remember { mutableStateOf<JFXWebView?>(null) }

    DisposableEffect(Unit) {
        val jfxWebView = JFXWebView(onClick, coordinates)
        jfxWebViewRef.value = jfxWebView

        onDispose {
            // Закриття ресурсів
//            jfxWebViewRef.value = null
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