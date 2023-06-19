package features

import implementation.WeatherbitApiImpl
import repository.WeatherApiRepository

val weatherApi: WeatherApiRepository = WeatherbitApiImpl()