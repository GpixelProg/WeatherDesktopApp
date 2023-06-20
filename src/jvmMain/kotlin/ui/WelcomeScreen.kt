package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ui.theme.backgroundGradient

object WelcomeScreen : Screen {
    @Composable
    override fun Content() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = backgroundGradient),
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center),
            ) {
                Text(
                    text = "Welcome to\nMeteo",
                    textAlign = TextAlign.Center,
                    fontSize = 48.sp,
                    color = Color.White.copy(alpha = 0.8f),
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                )

                Text(
                    text = "Get best weather forecast anywhere in the world",
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    color = Color.White.copy(alpha = 0.8f),
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 24.dp),
                )
            }

            val navigator = LocalNavigator.currentOrThrow

            TextButton(
                onClick = { navigator.push(SetupNotificationScreen) },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp),
            ) {
                Text(
                    text = "Next",
                    fontSize = 24.sp,
                    color = Color.White.copy(alpha = 0.9f),
                )
            }
        }
    }
}