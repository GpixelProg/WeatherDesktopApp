package repository

import data.MapCoordinates
import data.api.model.CurrentWeather

interface CurrentWeatherRepository {
    suspend fun getCurrentWeather(coordinates: MapCoordinates): CurrentWeather
}