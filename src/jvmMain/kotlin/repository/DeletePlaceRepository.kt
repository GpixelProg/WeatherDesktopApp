package repository

import data.MapCoordinates

interface DeletePlaceRepository {
    suspend fun delete(coordinates: MapCoordinates)
}