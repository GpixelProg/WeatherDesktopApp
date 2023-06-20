package features

import implementation.*
import repository.*

internal val addNewPlace : AddNewPlaceRepository = AddNewPlaceImpl()
internal val deletePlace : DeletePlaceRepository = DeletePlaceImpl()
internal val currentWeather : CurrentWeatherRepository = CurrentWeatherImpl()
internal val forecastHourly: ForecastHourlyRepository = ForecastHourlyImpl()
internal val forecastDaily: ForecastDailyRepository = ForecastDailyImpl()