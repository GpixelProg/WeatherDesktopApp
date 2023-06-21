package ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.MapCoordinates
import screen_model.SelectLocScreenModel
import ui.components.Map

data class SelectLocScreen(val coordinates: MapCoordinates) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = rememberScreenModel { SelectLocScreenModel(navigator) }

        Column {
            Map(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.9f),
                coordinates = coordinates,
                onClick = { screenModel.mapCoordinates.value = it },
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TextButton(onClick = { screenModel.back() }) {
                    Text("Back")
                }

                TextButton(onClick = { screenModel.addNewLocation() }) {
                    Text("Add")
                }

                TextButton(onClick = { screenModel.updateLocation(coordinates) }) {
                    Text("Update")
                }
            }
        }
    }
}
