package repository

import data.MapCoordinates
import data.api.model.CurrentWeather
import data.api.model.ForecastDaily
import data.api.model.ForecastHourly

interface DatabaseWeatherRepository {
    fun updateCurrentWeather(mapCoordinates: MapCoordinates, currentWeather: CurrentWeather)

    fun updateForecastHourly(mapCoordinates: MapCoordinates, forecastHourly: ForecastHourly)

    fun updateForecastDaily(mapCoordinates: MapCoordinates, forecastDaily: ForecastDaily)

    fun deleteWeatherData(mapCoordinates: MapCoordinates)

    fun getAllCurrentWeather(): List<CurrentWeather>

    fun getCurrentWeather(mapCoordinates: MapCoordinates): CurrentWeather

    fun getForecastHourly(mapCoordinates: MapCoordinates): ForecastHourly

    fun getForecastDaily(mapCoordinates: MapCoordinates): ForecastDaily
}