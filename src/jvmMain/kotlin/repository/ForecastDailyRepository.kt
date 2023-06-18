package repository

import data.MapCoordinates
import data.api.model.ForecastDaily

interface ForecastDailyRepository {
    suspend fun getForecastDaily(coordinates: MapCoordinates): ForecastDaily
}