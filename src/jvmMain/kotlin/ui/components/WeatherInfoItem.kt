package ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.LocalTime
import repository.SourceConfiguratorRepository
import ui.theme.hourlyPanelColor
import java.util.*


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeatherInfoGrid(
    modifier: Modifier,
    sourceConfigurator: SourceConfiguratorRepository
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(start = 20.dp, end = 24.dp, top = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            FeelsLike(
                sourceConfigurator = sourceConfigurator,
            )
        }

        item {
            Pressure(
                sourceConfigurator = sourceConfigurator,
            )
        }

        item {
            Humidity(
                sourceConfigurator = sourceConfigurator,
            )
        }

        item {
            Sunset(
                sourceConfigurator = sourceConfigurator,
            )
        }

        item {
            UvIndex(
                sourceConfigurator = sourceConfigurator,
            )
        }

        item {
            Wind(
                sourceConfigurator = sourceConfigurator,
            )
        }

        item {
            Rainfall(
                sourceConfigurator = sourceConfigurator,
            )
        }

        item {
            Visibility(
                sourceConfigurator = sourceConfigurator,
            )
        }

        item {
            AirQuality(
                sourceConfigurator = sourceConfigurator,
            )
        }
    }
}

@Composable
private fun WeatherInfoItem(
    modifier: Modifier = Modifier,
    title: String,
    titleColor: Color = Color.White,
    content: @Composable BoxScope.() -> Unit,
) {
    Surface(
        modifier = modifier.width(150.dp).height(150.dp),
        shape = RoundedCornerShape(5.dp),
        color = hourlyPanelColor
    ) {
        Column(
            Modifier.fillMaxSize().padding(10.dp)
        ) {
            Text(
                text = title,
                fontSize = 12.sp,
                color = titleColor.copy(alpha = 0.5f),
                modifier = Modifier
            )

            Box(Modifier.fillMaxSize()) {
                content()
            }
        }
    }
}

@Composable
private fun FeelsLike(
    sourceConfigurator: SourceConfiguratorRepository
) {
    WeatherInfoItem(
        title = "FEELS LIKE",
        titleColor = sourceConfigurator.feelsLike.activeColor
    ) {
        Text(
            text = "${sourceConfigurator.feelsLike.value}°",
            fontSize = 32.sp,
            color = sourceConfigurator.feelsLike.activeColor,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun Pressure(
    sourceConfigurator: SourceConfiguratorRepository
) {
    WeatherInfoItem(
        title = "PRESSURE",
        titleColor = sourceConfigurator.pressure.activeColor
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(
                text = "${sourceConfigurator.pressure.value}",
                fontSize = 32.sp,
                color = sourceConfigurator.pressure.activeColor,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Text(
                text = "hPa",
                fontSize = 16.sp,
                color = sourceConfigurator.pressure.activeColor,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
private fun Humidity(
    sourceConfigurator: SourceConfiguratorRepository
) {
    WeatherInfoItem(
        title = "HUMIDITY",
        titleColor = sourceConfigurator.humidity.activeColor
    ) {
        Text(
            text = "${sourceConfigurator.humidity.value}%",
            fontSize = 32.sp,
            color = sourceConfigurator.humidity.activeColor,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun Sunset(
    sourceConfigurator: SourceConfiguratorRepository
) {
    WeatherInfoItem(
        title = "SUNSET",
        titleColor = sourceConfigurator.sunset.activeColor
    ) {
        val sunset: LocalTime = sourceConfigurator.sunset.value as LocalTime
        Text(
            text = sunset.toSting(),
            fontSize = 32.sp,
            color = sourceConfigurator.sunrise.activeColor,
            modifier = Modifier.align(Alignment.TopStart).padding(top = 10.dp)
        )

        val sunrise: LocalTime = sourceConfigurator.sunrise.value as LocalTime
        Text(
            text = sunrise.toSting(),
            fontSize = 16.sp,
            color = sourceConfigurator.sunrise.activeColor,
            modifier = Modifier.align(Alignment.BottomStart).padding(bottom = 10.dp)
        )
    }
}

@Composable
private fun UvIndex(
    sourceConfigurator: SourceConfiguratorRepository
) {
    WeatherInfoItem(
        title = "UV INDEX",
        titleColor = sourceConfigurator.uvIndex.activeColor
    ) {
        Text(
            text = "${sourceConfigurator.uvIndex.value}",
            fontSize = 32.sp,
            color = sourceConfigurator.uvIndex.activeColor,
            modifier = Modifier.align(Alignment.TopStart).padding(top = 10.dp)
        )

        Text(
            text = getUVLevelDescription(sourceConfigurator.uvIndex.value as Int),
            fontSize = 16.sp,
            color = sourceConfigurator.uvIndex.activeColor,
            modifier = Modifier.align(Alignment.BottomStart).padding(bottom = 10.dp)
        )
    }
}

private fun getUVLevelDescription(uvIndex: Int): String {
    return when (uvIndex) {
        in 0..2 -> "Low UV"
        in 3..5 -> "Moderate UV"
        in 6..7 -> "High UV"
        in 8..10 -> "Very high UV"
        else -> "Extreme UV"
    }
}

@Composable
private fun Wind(
    sourceConfigurator: SourceConfiguratorRepository
) {
    WeatherInfoItem(
        title = "WIND",
        titleColor = sourceConfigurator.windSpeed.activeColor
    ) {
        Column(Modifier.align(Alignment.TopStart).padding(top = 10.dp)) {
            Text(
                text = "${sourceConfigurator.windSpeed.value}",
                fontSize = 32.sp,
                color = sourceConfigurator.windSpeed.activeColor,
                modifier = Modifier
            )

            Text(
                text = "km/h",
                fontSize = 16.sp,
                color = sourceConfigurator.windSpeed.activeColor,
                modifier = Modifier
            )
        }

        Text(
            text = sourceConfigurator.windDirectionFull.value.toString()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
            fontSize = 16.sp,
            color = sourceConfigurator.windDirectionFull.activeColor,
            modifier = Modifier.align(Alignment.BottomStart).padding(bottom = 10.dp)
        )
    }
}

@Composable
private fun Rainfall(
    sourceConfigurator: SourceConfiguratorRepository
) {
    WeatherInfoItem(
        title = "RAINFALL",
        titleColor = sourceConfigurator.rainfall.activeColor
    ) {
        Column(Modifier.align(Alignment.Center)) {
            Text(
                text = "${sourceConfigurator.rainfall.value}",
                fontSize = 32.sp,
                color = sourceConfigurator.rainfall.activeColor,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Text(
                text = "mm",
                fontSize = 16.sp,
                color = sourceConfigurator.rainfall.activeColor,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
private fun Visibility(
    sourceConfigurator: SourceConfiguratorRepository
) {
    WeatherInfoItem(
        title = "VISIBILITY",
        titleColor = sourceConfigurator.visibility.activeColor
    ) {
        Column(Modifier.align(Alignment.Center)) {
            Text(
                text = "${sourceConfigurator.visibility.value}",
                fontSize = 32.sp,
                color = sourceConfigurator.visibility.activeColor,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Text(
                text = "km",
                fontSize = 16.sp,
                color = sourceConfigurator.visibility.activeColor,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
private fun AirQuality(
    sourceConfigurator: SourceConfiguratorRepository
) {
    WeatherInfoItem(
        title = "AIR QUALITY",
        titleColor = sourceConfigurator.airQualityIndex.activeColor
    ) {
        Column(Modifier.align(Alignment.TopStart).padding(top = 10.dp)) {
            Text(
                text = "${sourceConfigurator.airQualityIndex.value}",
                fontSize = 32.sp,
                color = sourceConfigurator.airQualityIndex.activeColor,
                modifier = Modifier
            )

            Text(
                text = "ppm",
                fontSize = 16.sp,
                color = sourceConfigurator.airQualityIndex.activeColor,
                modifier = Modifier
            )
        }

        Text(
            text = getAirQualityLevelDescription(sourceConfigurator.airQualityIndex.value as Int),
            fontSize = 14.sp,
            color = sourceConfigurator.airQualityIndex.activeColor,
            modifier = Modifier.align(Alignment.BottomStart).padding(bottom = 10.dp).width(140.dp)
        )
    }
}

fun getAirQualityLevelDescription(airQualityIndex: Int): String {
    return when (airQualityIndex) {
        in 0..50 -> "Good air quality"
        in 51..100 -> "Moderate air quality"
        in 101..150 -> "Unhealthy for sensitive individuals"
        in 151..200 -> "Unhealthy"
        in 201..300 -> "Very unhealthy"
        else -> "Hazardous"
    }
}