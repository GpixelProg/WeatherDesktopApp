package ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seiko.imageloader.rememberAsyncImagePainter
import data.api.model.DailyWeather
import data.api.model.ForecastDaily
import features.getDayOfWeekName
import ui.theme.activeItemTextColor
import ui.theme.hourlyPanelColor

@Composable
fun DailyWeatherPanel(
    forecastDaily: ForecastDaily,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    activeItemIndex: Int = 0,
) {
    Surface(
        modifier = modifier.width(350.dp),
        shape = RoundedCornerShape(5.dp),
        color = hourlyPanelColor
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 5.dp, vertical = 5.dp)
        ) {
            Text(
                text = "16-DAY FORECAST",
                fontSize = 12.sp,
                color = Color.White.copy(alpha = 0.5f),
            )

            HorizontalLine(
                modifier = Modifier.padding(vertical = 5.dp),
                color = Color.White.copy(alpha = 0.5f),
            )

            forecastDaily.dailyWeather.forEachIndexed { index, it ->
                DailyWeatherItem(
                    dailyWeather = it,
                    onClick = { onClick(index) },
                    modifier = Modifier.padding(vertical = 5.dp),
                    isToday = index == 0,
                    activeColor = if (index == activeItemIndex && index != 0) activeItemTextColor
                    else Color.White,
                )
            }
        }
    }
}

@Composable
fun DailyWeatherItem(
    dailyWeather: DailyWeather,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isToday: Boolean = false,
    activeColor: Color = Color.White,
) {
    Row (
        modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 5.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = if (isToday) "Today"
            else dailyWeather.datestampLocal.getDayOfWeekName().substring(0, 3),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = activeColor.copy(alpha = 1f),
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .width(60.dp)
        )

        val painter = rememberAsyncImagePainter(dailyWeather.iconURL)
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .size(28.dp)
                .align(Alignment.CenterVertically)
        )

        Row(Modifier.align(Alignment.CenterVertically)) {
            Text(
                text = "${dailyWeather.minTemperature}°",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = activeColor.copy(alpha = 0.5f),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )

            TemperatureBar(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .align(Alignment.CenterVertically),
                minTemp = dailyWeather.minTemperature!!,
                maxTemp = dailyWeather.maxTemperature!!,
            )

            Text(
                text = "${dailyWeather.maxTemperature}°",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = activeColor.copy(alpha = 1f),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
    }
}