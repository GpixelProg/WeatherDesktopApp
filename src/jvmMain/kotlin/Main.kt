import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import data.MapCoordinates
import implementation.WeatherbitApiImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import repository.WeatherApiRepository
import ui.components.Map

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        val container = window

        val weatherApi: WeatherApiRepository = WeatherbitApiImpl()

        CoroutineScope(Job() + Dispatchers.Default).launch {
            val currentWeather = weatherApi.getCurrentWeather(MapCoordinates(49.2175, 28.4820))
//            println(currentWeather)

            val forecastHourly = weatherApi.getForecastHourly(MapCoordinates(49.2175, 28.4820))
//            println(forecastHourly)

            val forecastDaily = weatherApi.getForecastDaily(MapCoordinates(49.2175, 28.4820))
            println(forecastDaily)
        }

        var dataLocation by remember { mutableStateOf("") }

        Column {

            Text("Data location: $dataLocation")

            Map(
                onClick = { coordinates ->
                    dataLocation = "Latitude: ${coordinates.latitude} | Longitude: ${coordinates.longitude}"
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            )
        }
    }
}
