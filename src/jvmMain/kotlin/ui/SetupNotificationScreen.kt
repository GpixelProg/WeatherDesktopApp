package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Switch
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
import screen_model.SetupNotificationScreenModel
import ui.theme.backgroundGradient

object SetupNotificationScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = rememberScreenModel { SetupNotificationScreenModel(navigator) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = backgroundGradient),
        ) {
            Column(Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .padding(top = 48.dp)
                        .padding(horizontal = 24.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Turn on notifications",
                        fontSize = 24.sp,
                        color = Color.White.copy(alpha = 0.8f),
                        modifier = Modifier.padding(top = 8.dp),
                    )

                    Switch(
                        checked = screenModel.onNotification.value,
                        onCheckedChange = { screenModel.onNotification.value = it;
                            screenModel.switchOnNotification() },
                        modifier = Modifier.padding(end = 75.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(horizontal = 24.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Повідомити про перевищення температури з",
                        fontSize = 16.sp,
                        color = Color.White.copy(alpha = 0.8f),
                        modifier = Modifier.padding(top = 8.dp),
                    )

                    Row {
                        TextButton(
                            onClick = { if (screenModel.temperature.value > 0) screenModel.temperature.value--;
                                screenModel.onChangeTemperature() },
                            modifier = Modifier.padding(start = 8.dp),
                            enabled = screenModel.onNotification.value,
                        ) {
                            Text(
                                text = "-",
                                fontSize = 16.sp,
                                color = if (screenModel.onNotification.value)
                                    Color.White.copy(alpha = 0.8f) else Color.White.copy(alpha = 0.4f),
                                modifier = Modifier,
                            )
                        }

                        Text(
                            text = "${screenModel.temperature.value} °C",
                            fontSize = 16.sp,
                            color = if (screenModel.onNotification.value)
                                Color.White.copy(alpha = 0.8f) else Color.White.copy(alpha = 0.4f),
                            modifier = Modifier.padding(start = 8.dp, end = 8.dp).align(Alignment.CenterVertically),
                        )

                        TextButton(
                            onClick = { if (screenModel.temperature.value < 40)
                                screenModel.temperature.value++; screenModel.onChangeTemperature()},
                            modifier = Modifier.padding(end = 8.dp),
                            enabled = screenModel.onNotification.value,
                        ) {
                            Text(
                                text = "+",
                                fontSize = 16.sp,
                                color = if (screenModel.onNotification.value)
                                    Color.White.copy(alpha = 0.8f) else Color.White.copy(alpha = 0.4f),
                                modifier = Modifier,
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 24.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                TextButton(
                    onClick = { screenModel.back() },
                    modifier = Modifier
                ) {
                    Text(
                        text = "Back",
                        fontSize = 24.sp,
                        color = Color.White.copy(alpha = 0.9f),
                    )
                }

                TextButton(
                    onClick = { screenModel.next() },
                    modifier = Modifier
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
}