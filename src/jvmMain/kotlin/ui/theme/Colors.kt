package ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val backgroundGradient = Brush.verticalGradient(
    colors = listOf(Color(0xFF0E4485), Color(0xFF65A0DF)),
)

val panelColor = Color(0xFF285A91)
val hourlyPanelColor = Color(0xFF1A5FAB).copy(alpha = 0.7f)