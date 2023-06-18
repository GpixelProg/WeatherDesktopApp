package features

import implementation.DatabaseWeatherImpl
import repository.DatabaseWeatherRepository

/**
 * Singleton object for [DatabaseWeatherRepository] interface.
 */
val databaseWeather: DatabaseWeatherRepository = DatabaseWeatherImpl()