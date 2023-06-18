package data.database

import io.realm.kotlin.types.RealmObject

class DailyWeatherDB : RealmObject {
    var timestampLocal: String = ""
    var description: String = ""
    var iconURL: String = ""

    var temperature: Double? = null
    var feelsLike: Double? = null
    var pressure: Double? = null
    var humidity: Double? = null
    var uvIndex: Int? = null
    var windSpeed: Double? = null
    var windDirectionShort: String? = null
    var windDirectionFull: String? = null
    var rainfall: Double? = null
    var visibility: Double? = null
}
