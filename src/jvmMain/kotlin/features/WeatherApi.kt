package features

import implementation.WeatherbitApiImpl
import repository.WeatherApiRepository

internal val weatherApi: WeatherApiRepository = WeatherbitApiImpl()