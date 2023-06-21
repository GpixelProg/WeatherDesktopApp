package screen_model

import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import cafe.adriel.voyager.navigator.Navigator
import data.MapCoordinates
import data.Preferences
import data.api.model.CurrentWeather
import data.api.model.ForecastDaily
import data.api.model.ForecastHourly
import features.currentWeather
import features.deletePlace
import features.forecastDaily
import features.forecastHourly
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ui.SelectLocScreen

class HomeScreenModel(private val navigator: Navigator) : ScreenModel {
    var allCurrentWeather = mutableStateOf(emptyList<CurrentWeather>())
    var selectedCurrentWeather = mutableStateOf<CurrentWeather?>(null)
    var selectedForecastHourly = mutableStateOf<ForecastHourly?>(null)
    var selectedForecastDaily = mutableStateOf<ForecastDaily?>(null)

    init {
        coroutineScope.launch {
            while (true) {
                delay(Preferences.CURRENT_WEATHER_TIMEOUT)
                allCurrentWeather.value = currentWeather.getAllCurrentWeather()
            }
        }

        coroutineScope.launch {
            while (true) {
                selectedCurrentWeather.value?.let { currentWeather ->
                    selectedForecastHourly.value = forecastHourly.getForecastHourly(
                        MapCoordinates(currentWeather.latitude, currentWeather.longitude)
                    )
                }
                delay(Preferences.FORECAST_TIMEOUT)
            }
        }

        coroutineScope.launch {
            while (true) {
                selectedCurrentWeather.value?.let { currentWeather ->
                    selectedForecastDaily.value = forecastDaily.getForecastDaily(
                        MapCoordinates(currentWeather.latitude, currentWeather.longitude)
                    )
                }
                delay(Preferences.FORECAST_TIMEOUT)
            }
        }
    }

    fun loadAllCurrentWeather() {
        coroutineScope.launch {
            allCurrentWeather.value = currentWeather.getAllCurrentWeather()
            selectedCurrentWeather.value = allCurrentWeather.value.firstOrNull()
            selectedForecastHourly.value = selectedCurrentWeather.value?.let { currentWeather ->
                forecastHourly.getForecastHourly(
                    MapCoordinates(currentWeather.latitude, currentWeather.longitude)
                )
            }
            selectedForecastDaily.value = selectedCurrentWeather.value?.let { currentWeather ->
                forecastDaily.getForecastDaily(
                    MapCoordinates(currentWeather.latitude, currentWeather.longitude)
                )
            }
        }
    }

    fun onCurrentWeatherSelected(currentWeather: CurrentWeather) {
        selectedCurrentWeather.value = currentWeather
        coroutineScope.launch {
            selectedForecastHourly.value = forecastHourly.getForecastHourly(
                MapCoordinates(currentWeather.latitude, currentWeather.longitude)
            )
        }
        coroutineScope.launch {
            selectedForecastDaily.value = forecastDaily.getForecastDaily(
                MapCoordinates(currentWeather.latitude, currentWeather.longitude)
            )
        }
    }

    fun deleteWeatherPoint(currentWeatherDel: CurrentWeather) {
        if (allCurrentWeather.value.size > 1) {
            coroutineScope.launch {
                deletePlace.delete(MapCoordinates(currentWeatherDel.latitude, currentWeatherDel.longitude))
                allCurrentWeather.value = currentWeather.getAllCurrentWeather()
                selectedCurrentWeather.value = allCurrentWeather.value.firstOrNull()
            }
        }
    }

    fun openMap() {
        navigator.push(SelectLocScreen(
            MapCoordinates(selectedCurrentWeather.value!!.latitude, selectedCurrentWeather.value!!.longitude)
        ))
    }
}