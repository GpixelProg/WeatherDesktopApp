package ui

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChromeReaderMode
import androidx.compose.material.icons.filled.Room
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import data.TypeSource
import data.api.model.CurrentWeather
import data.api.model.ForecastDaily
import data.api.model.ForecastHourly
import features.NotificationSender
import features.getDayOfWeekName
import screen_model.HomeScreenModel
import ui.components.*
import ui.theme.backgroundGradient
import utils.toSting

object HomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = rememberScreenModel { HomeScreenModel(navigator) }
        val panelState = remember { PanelState() }

        val animatedSize = if (panelState.splitter.isResizing) {
            if (panelState.isExpanded) panelState.expandedSize else panelState.collapsedSize
        } else {
            animateDpAsState(
                if (panelState.isExpanded) panelState.expandedSize else panelState.collapsedSize,
                SpringSpec(stiffness = Spring.StiffnessLow)
            ).value
        }

        LaunchedEffect(Unit) {
            screenModel.loadAllCurrentWeather()
        }

        NotificationSender(screenModel.allCurrentWeather.value)

        VerticalSplittable(
            modifier = Modifier.fillMaxSize(),
            splitterState = panelState.splitter,
            onResize = {
                panelState.expandedSize = (panelState.expandedSize + it).coerceAtLeast(panelState.expandedSizeMin)
            }
        ) {
            ResizablePanel(
                modifier = Modifier.width(animatedSize).fillMaxHeight(),
                state = panelState
            ) {
                if (screenModel.allCurrentWeather.value.isEmpty()) LoadingCircle()

                LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 16.dp)) {
                    items(screenModel.allCurrentWeather.value) { currentWeather ->

                        val selectedCurrentWeather = screenModel.selectedCurrentWeather.value

                        ShortWeatherPanel(
                            modifier = Modifier,
                            currentWeather = currentWeather,
                            selected = currentWeather.longitude == selectedCurrentWeather?.longitude &&
                                currentWeather.latitude == selectedCurrentWeather.latitude,
                            onClick = {
                                screenModel.onCurrentWeatherSelected(it)
                            },
                            onDelete = {
                                screenModel.deleteWeatherPoint(currentWeather)
                            }
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush = backgroundGradient)
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 0.dp)
                            .padding(top = 0.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(
                            onClick = {
                                panelState.isExpanded = !panelState.isExpanded
                            }
                        ) {
                            Icon(
                                Icons.Default.ChromeReaderMode,
                                contentDescription = null,
                                tint = Color.White.copy(alpha = 0.5f)
                            )
                        }

                        Row(
                            modifier = Modifier
                                .clickable {
                                    screenModel.openMap()
                                }
                                .padding(12.dp)
                        ) {
                            Text(
                                text = "Map",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White.copy(alpha = 0.5f),
                                modifier = Modifier
                                    .padding(end = 0.dp)
                                    .align(Alignment.CenterVertically)
                            )

                            Icon(
                                Icons.Default.Room,
                                contentDescription = null,
                                tint = Color.White.copy(alpha = 0.5f),
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }

                    if (screenModel.selectedCurrentWeather.value != null &&
                        screenModel.selectedForecastHourly.value != null &&
                        screenModel.selectedForecastDaily.value != null) {

                        screenModel.sourceConfigurator.value = InformantTrigger(
                            currentWeather = screenModel.selectedCurrentWeather.value!!,
                            forecastHourly = screenModel.selectedForecastHourly.value!!,
                            forecastDaily = screenModel.selectedForecastDaily.value!!,
                            sourceData = screenModel.sourceData.value,
                        )

                        WeatherMain(
                            currentWeather = screenModel.selectedCurrentWeather.value!!,
                            forecastHourly = screenModel.selectedForecastHourly.value!!,
                            forecastDaily = screenModel.selectedForecastDaily.value!!,
                            screenModel = screenModel
                        )
                    } else {
                        LoadingCircle()
                    }
                }
            }
        }
    }

    @Composable
    fun ColumnScope.WeatherMain(
        currentWeather: CurrentWeather,
        forecastHourly: ForecastHourly,
        forecastDaily: ForecastDaily,
        screenModel: HomeScreenModel
    ) {
        val stateVertical = rememberScrollState(0)

        Box(Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .verticalScroll(stateVertical)
                    .fillMaxSize()
                    .padding(end = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 50.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = currentWeather.city,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White.copy(alpha = 1f),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )

                    Text(
                        text = "${currentWeather.temperature!!.toInt()}°",
                        fontSize = 52.sp,
                        fontWeight = FontWeight.ExtraLight,
                        color = Color.White.copy(alpha = 1f),
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    Text(
                        text = currentWeather.weatherDescription,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White.copy(alpha = 1f),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                }

                HourlyWeatherPanel(
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .padding(horizontal = 24.dp),
                    currentWeather = currentWeather,
                    forecastHourly = forecastHourly,
                    lastUpdate = "Last update: ${currentWeather.lastUpdate.date.getDayOfWeekName()} " +
                            currentWeather.lastUpdate.time.toSting(),
                    onClick = {
                        screenModel.hourlyWeatherPanelOnClick(it)
                    },
                    activeItemIndex = if (screenModel.sourceData.value.typeSource == TypeSource.HOURLY) {
                        screenModel.sourceData.value.index ?: 0
                    } else {
                        0
                    }
                )

                Row {
                    DailyWeatherPanel(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .padding(start = 24.dp),
                        forecastDaily = forecastDaily,
                        onClick = {
                            screenModel.dailyWeatherPanelOnClick(it)
                        },
                        activeItemIndex = if (screenModel.sourceData.value.typeSource == TypeSource.DAILY) {
                            screenModel.sourceData.value.index ?: 0
                        } else {
                            0
                        }
                    )

                    WeatherInfoGrid(
                        modifier = Modifier.fillMaxWidth().height(500.dp),
                        sourceConfigurator = screenModel.sourceConfigurator.value!!
                    )
                }
            }

            VerticalScrollbar(
                modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                adapter = rememberScrollbarAdapter(stateVertical),
            )
        }
    }
}
