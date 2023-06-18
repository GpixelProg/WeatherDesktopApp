package implementation

import data.MapCoordinates
import data.api.model.CurrentWeather
import data.api.model.ForecastDaily
import data.api.model.ForecastHourly
import data.database.*
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import repository.DatabaseWeatherRepository

class DatabaseWeatherImpl : DatabaseWeatherRepository {
    private val realmCurrent by lazy {
        val config = RealmConfiguration.create(schema = setOf(
            CurrentWeatherDB::class,
            ForecastHourlyDB::class,
            ForecastDailyDB::class,
            HourlyWeatherDB::class,
            DailyWeatherDB::class
        ))
        Realm.open(config)
    }

    override fun updateCurrentWeather(mapCoordinates: MapCoordinates, currentWeather: CurrentWeather) {
        val item = realmCurrent.query<CurrentWeatherDB>(
            "latitude = ${mapCoordinates.latitude} AND longitude = ${mapCoordinates.longitude}"
        ).find()

        if (item.isNotEmpty()) {
            realmCurrent.writeBlocking {
                val document = item[0]

                document.lastUpdate = currentWeather.lastUpdate.toString()
                document.city = currentWeather.city
                document.longitude = currentWeather.longitude
                document.latitude = currentWeather.latitude
                document.weatherDescription = currentWeather.weatherDescription
                document.iconURL = currentWeather.iconURL

                document.temperature = currentWeather.temperature
                document.feelsLike = currentWeather.feelsLike
                document.pressure = currentWeather.pressure
                document.humidity = currentWeather.humidity
                document.sunrise = currentWeather.sunrise.toString()
                document.sunset = currentWeather.sunset.toString()
                document.uvIndex = currentWeather.uvIndex
                document.windSpeed = currentWeather.windSpeed
                document.windDirectionShort = currentWeather.windDirectionShort
                document.windDirectionFull = currentWeather.windDirectionFull
                document.rainfall = currentWeather.rainfall
                document.visibility = currentWeather.visibility
                document.airQualityIndex = currentWeather.airQualityIndex
            }
            println("DB CurrentWeather updated")
        } else {
            realmCurrent.writeBlocking {
                copyToRealm(CurrentWeatherDB().apply {

                    lastUpdate = currentWeather.lastUpdate.toString()
                    city = currentWeather.city
                    longitude = currentWeather.longitude
                    latitude = currentWeather.latitude
                    weatherDescription = currentWeather.weatherDescription
                    iconURL = currentWeather.iconURL

                    temperature = currentWeather.temperature
                    feelsLike = currentWeather.feelsLike
                    pressure = currentWeather.pressure
                    humidity = currentWeather.humidity
                    sunrise = currentWeather.sunrise.toString()
                    sunset = currentWeather.sunset.toString()
                    uvIndex = currentWeather.uvIndex
                    windSpeed = currentWeather.windSpeed
                    windDirectionShort = currentWeather.windDirectionShort
                    windDirectionFull = currentWeather.windDirectionFull
                    rainfall = currentWeather.rainfall
                    visibility = currentWeather.visibility
                    airQualityIndex = currentWeather.airQualityIndex
                })
            }
            println("DB CurrentWeather added")
        }
    }

    override fun updateForecastHourly(mapCoordinates: MapCoordinates, forecastHourly: ForecastHourly) {
        TODO("Not yet implemented")
    }

    override fun updateForecastDaily(mapCoordinates: MapCoordinates, forecastDaily: ForecastDaily) {
        TODO("Not yet implemented")
    }

    override fun deleteWeatherData(mapCoordinates: MapCoordinates) {
        TODO("Not yet implemented")
    }

    override fun getAllCurrentWeather(): List<CurrentWeather> {
        TODO("Not yet implemented")
    }

    override fun getCurrentWeather(mapCoordinates: MapCoordinates): CurrentWeather {
        TODO("Not yet implemented")
    }

    override fun getForecastHourly(mapCoordinates: MapCoordinates): ForecastHourly {
        TODO("Not yet implemented")
    }

    override fun getForecastDaily(mapCoordinates: MapCoordinates): ForecastDaily {
        TODO("Not yet implemented")
    }
}