package data.database

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId

class ForecastDailyDB : RealmObject {
    @PrimaryKey
    var _id: ObjectId = BsonObjectId()
    var lastUpdate: String = ""
    var city: String = ""
    var longitude: Double = 0.0
    var latitude: Double = 0.0
    var timezone: String = ""
    var dailyWeather: RealmList<DailyWeatherDB> = realmListOf()
}