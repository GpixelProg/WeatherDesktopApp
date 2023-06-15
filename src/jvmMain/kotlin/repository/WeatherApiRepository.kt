package repository

import data.MapCoordinates
import data.api.model.AlertWeather
import data.api.model.CurrentWeather
import data.api.model.ForecastDaily
import data.api.model.ForecastHourly

interface WeatherApiRepository {
    suspend fun getCurrentWeather(mapCoordinates: MapCoordinates): CurrentWeather

    suspend fun getForecastHourly(mapCoordinates: MapCoordinates): ForecastHourly

    suspend fun getForecastDaily(mapCoordinates: MapCoordinates): ForecastDaily

    suspend fun getAlerts(mapCoordinates: MapCoordinates): AlertWeather
}