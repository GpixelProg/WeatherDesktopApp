package ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.rememberAsyncImagePainter
import data.api.model.CurrentWeather
import data.api.model.ForecastHourly
import kotlinx.datetime.LocalTime
import ui.theme.hourlyPanelColor

@Composable
fun HourlyWeatherPanel(
    currentWeather: CurrentWeather,
    forecastHourly: ForecastHourly,
    lastUpdate: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(5.dp),
        color = hourlyPanelColor
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 5.dp, vertical = 5.dp)
        ) {
            Text(
                text = lastUpdate,
                fontSize = 12.sp,
                color = Color.White.copy(alpha = 0.5f),
            )

            HorizontalLine(
                modifier = Modifier.padding(vertical = 5.dp),
                color = Color.White.copy(alpha = 0.5f),
            )

            val stateHorizontal = rememberScrollState(0)

            CompositionLocalProvider(
                LocalImageLoader provides generateImageLoader(),
            ) {
                Row(
                    modifier = Modifier
                        .horizontalScroll(stateHorizontal)
                ) {
                    HourlyWeatherItem(
                        time = currentWeather.lastUpdate.time,
                        temperature = currentWeather.temperature!!,
                        iconUrl = currentWeather.iconURL,
                        onClick = onClick,
                        isNow = true,
                    )

                    forecastHourly.hourlyWeather.forEachIndexed { index, hourlyWeather ->
                        HourlyWeatherItem(
                            time = hourlyWeather.timestampLocal,
                            temperature = hourlyWeather.temperature!!,
                            iconUrl = hourlyWeather.iconURL,
                            onClick = onClick,
                        )
                    }
                }
            }

            HorizontalScrollbar(
                modifier = Modifier.padding(top = 5.dp),
                adapter = rememberScrollbarAdapter(stateHorizontal),
            )
        }
    }
}

@Composable
fun HourlyWeatherItem(
    time: LocalTime,
    temperature: Double,
    iconUrl: String,
    onClick: () -> Unit,
    isNow: Boolean = false,
) {
    Column(
        modifier = Modifier.padding(horizontal = 12.dp)
    ) {
        Text(
            text = if (isNow) "Now" else "${time.hour}",
            fontSize = 12.sp,
            color = Color.White.copy(alpha = 1f),
            modifier = Modifier.padding(top = 5.dp).align(Alignment.CenterHorizontally)
        )

        val painter = rememberAsyncImagePainter(iconUrl)
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .padding(top = 12.dp)
                .size(28.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = "${temperature}°",
            fontSize = 14.sp,
            color = Color.White.copy(alpha = 1f),
            modifier = Modifier.padding(top = 12.dp).align(Alignment.CenterHorizontally)
        )
    }
}