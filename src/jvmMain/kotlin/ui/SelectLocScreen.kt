package ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import data.MapCoordinates

data class SelectLocScreen(val coordinates: MapCoordinates) : Screen {

        @Composable
        override fun Content() {

        }
}
