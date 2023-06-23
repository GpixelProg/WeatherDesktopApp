package features

import implementation.DatabaseNotificationImpl
import implementation.DatabaseWeatherImpl
import repository.DatabaseNotificationRepository
import repository.DatabaseWeatherRepository

/**
 * Singleton object for [DatabaseWeatherRepository] interface.
 */
internal val databaseWeather: DatabaseWeatherRepository = DatabaseWeatherImpl()

internal val databaseNotification: DatabaseNotificationRepository = DatabaseNotificationImpl()