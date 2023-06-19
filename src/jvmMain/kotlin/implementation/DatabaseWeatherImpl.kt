package implementation

import data.MapCoordinates
import data.api.model.*
import data.database.*
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.query.RealmResults
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import repository.DatabaseWeatherRepository

class DatabaseWeatherImpl : DatabaseWeatherRepository {
    private val realm by lazy {
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
        val item: RealmResults<CurrentWeatherDB> = realm.query<CurrentWeatherDB>(
            "latitude = ${mapCoordinates.latitude} AND longitude = ${mapCoordinates.longitude}"
        ).find()

        if (item.isNotEmpty()) {
            realm.writeBlocking {
                val document = findLatest(item[0])!!

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
            realm.writeBlocking {
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
        val item: RealmResults<ForecastHourlyDB> = realm.query<ForecastHourlyDB>(
            "latitude = ${mapCoordinates.latitude} AND longitude = ${mapCoordinates.longitude}"
        ).find()

        val hourlyWeather = realmListOf<HourlyWeatherDB>()
        forecastHourly.hourlyWeather.forEach {
            hourlyWeather.add(HourlyWeatherDB().apply {
                timestampLocal = it.timestampLocal.toString()
                description = it.description
                iconURL = it.iconURL

                temperature = it.temperature ?: 500.0
                feelsLike = it.feelsLike ?: 500.0
                pressure = it.pressure ?: 0.0
                humidity = it.humidity ?: 500.0
                uvIndex = it.uvIndex ?: 500
                windSpeed = it.windSpeed ?: 500.0
                windDirectionShort = it.windDirectionShort ?: ""
                windDirectionFull = it.windDirectionFull ?: ""
                rainfall = it.rainfall ?: 500.0
                visibility = it.visibility ?: 500.0
            })
        }

        if (item.isNotEmpty()) {
            realm.writeBlocking {
                delete(findLatest(item[0])!!.hourlyWeather)

                val document = findLatest(item[0])!!

                document.lastUpdate = forecastHourly.lastUpdate.toString()
                document.city = forecastHourly.city
                document.longitude = forecastHourly.longitude
                document.latitude = forecastHourly.latitude
                document.timezone = forecastHourly.timezone

                document.hourlyWeather = hourlyWeather
            }
            println("DB ForecastHourly updated")
        } else {
            realm.writeBlocking {
                copyToRealm(ForecastHourlyDB().apply {
                    lastUpdate = forecastHourly.lastUpdate.toString()
                    city = forecastHourly.city
                    longitude = forecastHourly.longitude
                    latitude = forecastHourly.latitude
                    timezone = forecastHourly.timezone

                    this.hourlyWeather = hourlyWeather
                })
            }
            println("DB ForecastHourly added")
        }
    }

    override fun updateForecastDaily(mapCoordinates: MapCoordinates, forecastDaily: ForecastDaily) {
        val item: RealmResults<ForecastDailyDB> = realm.query<ForecastDailyDB>(
            "latitude = ${mapCoordinates.latitude} AND longitude = ${mapCoordinates.longitude}"
        ).find()

        val dailyWeather = realmListOf<DailyWeatherDB>()
        forecastDaily.dailyWeather.forEach {
            dailyWeather.add(DailyWeatherDB().apply {
                datestampLocal = it.datestampLocal.toString()
                description = it.description
                iconURL = it.iconURL

                temperature = it.temperature ?: 500.0
                minTemperature = it.minTemperature ?: 500.0
                maxTemperature = it.maxTemperature ?: 500.0
                pressure = it.pressure ?: 0.0
                humidity = it.humidity ?: 500.0
                sunrise = it.sunrise.toString()
                sunset = it.sunset.toString()
                uvIndex = it.uvIndex ?: 500
                windSpeed = it.windSpeed ?: 500.0
                windDirectionShort = it.windDirectionShort ?: ""
                windDirectionFull = it.windDirectionFull ?: ""
                rainfall = it.rainfall ?: 500.0
                visibility = it.visibility ?: 500.0
            })
        }

        if (item.isNotEmpty()) {
            realm.writeBlocking {
                delete(findLatest(item[0])!!.dailyWeather)

                val document = findLatest(item[0])!!

                document.lastUpdate = forecastDaily.lastUpdate.toString()
                document.city = forecastDaily.city
                document.longitude = forecastDaily.longitude
                document.latitude = forecastDaily.latitude
                document.timezone = forecastDaily.timezone

                document.dailyWeather = dailyWeather
            }
            println("DB ForecastDaily updated")
        } else {
            realm.writeBlocking {
                copyToRealm(ForecastDailyDB().apply {
                    lastUpdate = forecastDaily.lastUpdate.toString()
                    city = forecastDaily.city
                    longitude = forecastDaily.longitude
                    latitude = forecastDaily.latitude
                    timezone = forecastDaily.timezone

                    this.dailyWeather = dailyWeather
                })
            }
            println("DB ForecastDaily added")
        }
    }

    override fun deleteWeatherData(mapCoordinates: MapCoordinates) {
        val currentWeather: RealmResults<CurrentWeatherDB> = realm.query<CurrentWeatherDB>(
            "latitude = ${mapCoordinates.latitude} AND longitude = ${mapCoordinates.longitude}"
        ).find()

        val forecastHourly: ForecastHourlyDB? = realm.query<ForecastHourlyDB>(
            "latitude = ${mapCoordinates.latitude} AND longitude = ${mapCoordinates.longitude}"
        ).first().find()

        val forecastDaily: ForecastDailyDB? = realm.query<ForecastDailyDB>(
            "latitude = ${mapCoordinates.latitude} AND longitude = ${mapCoordinates.longitude}"
        ).first().find()

        if (currentWeather.isNotEmpty()) {
            realm.writeBlocking {
                delete(findLatest(currentWeather[0])!!)
            }
            println("DB CurrentWeather deleted")
        }

        if (forecastHourly != null) {
            realm.writeBlocking {
                delete(findLatest(forecastHourly)!!.hourlyWeather)
                delete(findLatest(forecastHourly)!!)
            }
            println("DB ForecastHourly deleted")
        }

        if (forecastDaily != null) {
            realm.writeBlocking {
                delete(findLatest(forecastDaily)!!.dailyWeather)
                delete(findLatest(forecastDaily)!!)
            }
            println("DB ForecastDaily deleted")
        }
    }

    override fun getAllCurrentWeather(): List<CurrentWeather> {
        val currentWeather: RealmResults<CurrentWeatherDB> = realm.query<CurrentWeatherDB>().find()
        val currentWeatherList = mutableListOf<CurrentWeather>()

        currentWeather.forEach {
            val weather = CurrentWeather(
                lastUpdate = LocalDateTime.parse(it.lastUpdate),
                city = it.city,
                longitude = it.longitude,
                latitude = it.latitude,
                weatherDescription = it.weatherDescription,
                iconURL = it.iconURL,

                temperature = it.temperature,
                feelsLike = it.feelsLike,
                pressure = it.pressure,
                humidity = it.humidity,
                sunrise = LocalTime.parse(it.sunrise!!),
                sunset = LocalTime.parse(it.sunset!!),
                uvIndex = it.uvIndex,
                windSpeed = it.windSpeed,
                windDirectionShort = it.windDirectionShort,
                windDirectionFull = it.windDirectionFull,
                rainfall = it.rainfall,
                visibility = it.visibility,
                airQualityIndex = it.airQualityIndex,
            )
            currentWeatherList.add(weather)
        }

        return currentWeatherList
    }

    override fun getCurrentWeather(mapCoordinates: MapCoordinates): CurrentWeather {
        val currentWeather: CurrentWeatherDB? = realm.query<CurrentWeatherDB>(
            "latitude = ${mapCoordinates.latitude} AND longitude = ${mapCoordinates.longitude}"
        ).first().find()

        return CurrentWeather(
            lastUpdate = LocalDateTime.parse(currentWeather!!.lastUpdate),
            city = currentWeather.city,
            longitude = currentWeather.longitude,
            latitude = currentWeather.latitude,
            weatherDescription = currentWeather.weatherDescription,
            iconURL = currentWeather.iconURL,

            temperature = currentWeather.temperature,
            feelsLike = currentWeather.feelsLike,
            pressure = currentWeather.pressure,
            humidity = currentWeather.humidity,
            sunrise = LocalTime.parse(currentWeather.sunrise!!),
            sunset = LocalTime.parse(currentWeather.sunset!!),
            uvIndex = currentWeather.uvIndex,
            windSpeed = currentWeather.windSpeed,
            windDirectionShort = currentWeather.windDirectionShort,
            windDirectionFull = currentWeather.windDirectionFull,
            rainfall = currentWeather.rainfall,
            visibility = currentWeather.visibility,
            airQualityIndex = currentWeather.airQualityIndex,
        )
    }

    override fun getForecastHourly(mapCoordinates: MapCoordinates): ForecastHourly {
        val forecastHourly: ForecastHourlyDB? = realm.query<ForecastHourlyDB>(
            "latitude = ${mapCoordinates.latitude} AND longitude = ${mapCoordinates.longitude}"
        ).first().find()

        val hourlyWeather = mutableListOf<HourlyWeather>()
        forecastHourly!!.hourlyWeather.forEach {
            hourlyWeather.add(HourlyWeather(
                timestampLocal = LocalTime.parse(it.timestampLocal),
                description = it.description,
                iconURL = it.iconURL,

                temperature = it.temperature,
                feelsLike = it.feelsLike,
                pressure = it.pressure,
                humidity = it.humidity,
                uvIndex = it.uvIndex,
                windSpeed = it.windSpeed,
                windDirectionShort = it.windDirectionShort,
                windDirectionFull = it.windDirectionFull,
                rainfall = it.rainfall,
                visibility = it.visibility,
            ))
        }

        return ForecastHourly(
            lastUpdate = LocalDateTime.parse(forecastHourly.lastUpdate),
            city = forecastHourly.city,
            longitude = forecastHourly.longitude,
            latitude = forecastHourly.latitude,
            timezone = forecastHourly.timezone,

            hourlyWeather = hourlyWeather,
        )
    }

    override fun getForecastDaily(mapCoordinates: MapCoordinates): ForecastDaily {
        val forecastDaily: ForecastDailyDB? = realm.query<ForecastDailyDB>(
            "latitude = ${mapCoordinates.latitude} AND longitude = ${mapCoordinates.longitude}"
        ).first().find()

        val dailyWeather = mutableListOf<DailyWeather>()
        forecastDaily!!.dailyWeather.forEach {
            dailyWeather.add(DailyWeather(
                datestampLocal = LocalDate.parse(it.datestampLocal),
                description = it.description,
                iconURL = it.iconURL,

                temperature = it.temperature,
                minTemperature = it.minTemperature,
                maxTemperature = it.maxTemperature,
                pressure = it.pressure,
                humidity = it.humidity,
                sunrise = LocalTime.parse(it.sunrise),
                sunset = LocalTime.parse(it.sunset),
                uvIndex = it.uvIndex,
                windSpeed = it.windSpeed,
                windDirectionShort = it.windDirectionShort,
                windDirectionFull = it.windDirectionFull,
                rainfall = it.rainfall,
                visibility = it.visibility,
            ))
        }

        return ForecastDaily(
            lastUpdate = LocalDateTime.parse(forecastDaily.lastUpdate),
            city = forecastDaily.city,
            longitude = forecastDaily.longitude,
            latitude = forecastDaily.latitude,
            timezone = forecastDaily.timezone,

            dailyWeather = dailyWeather,
        )
    }
}