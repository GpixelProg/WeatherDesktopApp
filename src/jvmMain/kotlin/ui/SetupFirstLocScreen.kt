package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.MapCoordinates
import screen_model.SetupFirstLocScreenModel
import ui.components.Map
import ui.theme.backgroundGradient

object SetupFirstLocScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = rememberScreenModel { SetupFirstLocScreenModel(navigator) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = backgroundGradient),
        ) {
            Text(
                "Choose your first weather location ${if (screenModel.coordinates.value != null) "✅" else ""}",
                fontSize = 18.sp,
                color = Color.White.copy(alpha = 0.8f),
                modifier = Modifier.padding(vertical = 8.dp).padding(start = 16.dp)
            )

            Map(
                onClick = { coordinates ->
                    screenModel.coordinates.value = coordinates
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.92f)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
//                TextButton(
//                    onClick = { screenModel.back() },
//                    modifier = Modifier
//                ) {
//                    Text(
//                        text = "Back",
//                        fontSize = 24.sp,
//                        color = Color.White.copy(alpha = 0.9f),
//                    )
//                }

                TextButton(
                    onClick = { screenModel.next() },
                    modifier = Modifier,
                    enabled = screenModel.coordinates.value != null
                ) {
                    Text(
                        text = "Next",
                        fontSize = 24.sp,
                        color = if (screenModel.coordinates.value != null)
                            Color.White else Color.White.copy(alpha = 0.3f),
                    )
                }
            }
        }
    }
}