package data.database

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class CurrentWeatherDB : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var lastUpdate: String = ""
    var city: String = ""
    var longitude: Double = 0.0
    var latitude: Double = 0.0
    var weatherDescription: String = ""
    var iconURL: String = ""

    var temperature: Double? = null
    var feelsLike: Double? = null
    var pressure: Double? = null
    var humidity: Double? = null
    var sunrise: String? = null
    var sunset: String? = null
    var uvIndex: Int? = null
    var windSpeed: Double? = null
    var windDirectionShort: String? = null
    var windDirectionFull: String? = null
    var rainfall: Double? = null
    var visibility: Double? = null
    var airQualityIndex: Int? = null
}