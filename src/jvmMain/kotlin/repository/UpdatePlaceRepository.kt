package repository

import data.MapCoordinates

interface UpdatePlaceRepository {
    suspend fun updatePlace(oldCoordinates: MapCoordinates, newCoordinates: MapCoordinates)
}