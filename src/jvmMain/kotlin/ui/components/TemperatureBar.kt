package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.unit.dp
import kotlin.math.abs
import kotlin.math.roundToInt

@Composable
fun TemperatureBar(
    modifier: Modifier = Modifier,
    minTemp: Double,
    maxTemp: Double,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        pickColorByTemperature(minTemp),
                        pickColorByTemperature(maxTemp)
                    )
                )
            )
            .size(width = 100.dp, height = 5.dp)
    ) {

    }
}

private fun pickColorByTemperature(temperature: Double): Color {
    val minTemperature = -25f
    val maxTemperature = 35f

    val hueRange = 240f // 240 degrees (blue to red)
    val hue = when {
        temperature <= minTemperature -> 240f // Blue color
        temperature >= maxTemperature -> 0f   // Red color
        else -> {
            val temperatureRange = maxTemperature - minTemperature
            val temperatureOffset = temperature - minTemperature
            val hueStep = hueRange / temperatureRange
            hueRange - (hueStep * temperatureOffset)
        }
    }

    return Color.HSVToColor(floatArrayOf(hue.toFloat(), 1f, 1f))
}

private fun Color.Companion.HSVToColor(hsv: FloatArray): Color {
    val (h, s, v) = hsv
    val c = v * s
    val x = c * (1 - Math.abs((h / 60) % 2 - 1))
    val m = v - c
    val (r, g, b) = when {
        h < 60 -> Triple(c, x, 0f)
        h < 120 -> Triple(x, c, 0f)
        h < 180 -> Triple(0f, c, x)
        h < 240 -> Triple(0f, x, c)
        h < 300 -> Triple(x, 0f, c)
        else -> Triple(c, 0f, x)
    }
    return Color(r + m, g + m, b + m)
}