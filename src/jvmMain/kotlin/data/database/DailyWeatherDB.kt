package data.database

import io.realm.kotlin.types.RealmObject

class DailyWeatherDB : RealmObject {
    var datestampLocal: String = ""
    var description: String = ""
    var iconURL: String = ""

    var temperature: Double = 500.0
    var maxTemperature: Double = 500.0
    var minTemperature: Double = 500.0
    var pressure: Double = 500.0
    var humidity: Double = 500.0
    var sunrise: String = ""
    var sunset: String = ""
    var uvIndex: Int = 500
    var windSpeed: Double = 500.0
    var windDirectionShort: String = ""
    var windDirectionFull: String = ""
    var rainfall: Double = 500.0
    var visibility: Double = 500.0
}
