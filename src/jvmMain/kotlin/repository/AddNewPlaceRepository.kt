package repository

import data.MapCoordinates

interface AddNewPlaceRepository {
    fun addNewPlace(coordinates: MapCoordinates)
}