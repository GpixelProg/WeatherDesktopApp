package data.database

import io.realm.kotlin.types.RealmObject

class HourlyWeatherDB : RealmObject {
    var timestampLocal: String = ""
    var description: String = ""
    var iconURL: String = ""

    var temperature: Double = 500.0
    var feelsLike: Double = 500.0
    var pressure: Double = 0.0
    var humidity: Double = 500.0
    var uvIndex: Int = 500
    var windSpeed: Double = 500.0
    var windDirectionShort: String = ""
    var windDirectionFull: String = ""
    var rainfall: Double = 500.0
    var visibility: Double = 500.0
}
