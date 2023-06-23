package repository

import data.MapCoordinates
import data.api.model.ForecastHourly

interface ForecastHourlyRepository {
    suspend fun getForecastHourly(coordinates: MapCoordinates): ForecastHourly
}