package repository

import data.MapCoordinates

interface AddNewPlaceRepository {
    suspend fun addNewPlace(coordinates: MapCoordinates)
}