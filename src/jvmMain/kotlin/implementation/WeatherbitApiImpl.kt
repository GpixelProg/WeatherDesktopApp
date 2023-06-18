package implementation

import api.httpClient
import data.MapCoordinates
import data.api.model.*
import data.api.weatherbit.URLs
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.datetime.*
import repository.WeatherApiRepository

class WeatherbitApiImpl : WeatherApiRepository {
    override suspend fun getCurrentWeather(mapCoordinates: MapCoordinates): CurrentWeather {
        val result: data.api.weatherbit.current.CurrentWeather
            = httpClient().get(URLs().getCurrentWeatherURL(mapCoordinates.latitude, mapCoordinates.longitude)).body()

        val data = result.data[0]
        return CurrentWeather(
            lastUpdate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            city = data.city_name ?: "",
            longitude = data.lon ?: 0.0,
            latitude = data.lat ?: 0.0,
            weatherDescription = data.weather?.description ?: "",
            iconURL = URLs().getIconURL(data.weather?.icon ?: ""),

            temperature = data.temp,
            feelsLike = data.app_temp,
            pressure = data.pres,
            humidity = data.rh,
            sunrise = LocalTime.parse(data.sunrise!!),
            sunset = LocalTime.parse(data.sunset!!),
            uvIndex = data.uv!!.toInt(),
            windSpeed = data.wind_spd,
            windDirectionShort = data.wind_cdir,
            windDirectionFull = data.wind_cdir_full,
            rainfall = data.precip,
            visibility = data.vis,
            airQualityIndex = data.aqi
        )
    }

    override suspend fun getForecastHourly(mapCoordinates: MapCoordinates): ForecastHourly {
        val result: data.api.weatherbit.hourly.HourlyWeather
            = httpClient().get(URLs().getForecastHourlyURL(mapCoordinates.latitude, mapCoordinates.longitude)).body()

        val hourlyWeatherList = mutableListOf<HourlyWeather>()

        result.data?.forEach { data ->
            hourlyWeatherList.add(
                HourlyWeather(
                    timestampLocal = LocalDateTime.parse(data?.timestampLocal!!).time,
                    description = data.weather?.description ?: "",
                    iconURL = URLs().getIconURL(data.weather?.icon ?: ""),

                    temperature = data.temp,
                    feelsLike = data.appTemp,
                    pressure = data.pres,
                    humidity = data.rh,
                    uvIndex = data.uv?.toInt() ?: 0,
                    windSpeed = data.windSpd,
                    windDirectionShort = data.windCdir,
                    windDirectionFull = data.windCdirFull,
                    rainfall = data.precip,
                    visibility = data.vis,
                )
            )
        }

        return ForecastHourly(
            lastUpdate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            city = result.cityName,
            longitude = result.lon,
            latitude = result.lat,
            timezone = result.timezone,
            hourlyWeather = hourlyWeatherList
        )
    }

    override suspend fun getForecastDaily(mapCoordinates: MapCoordinates): ForecastDaily {
        val result: data.api.weatherbit.daily.DailyWeather
            = httpClient().get(URLs().getForecastDailyURL(mapCoordinates.latitude, mapCoordinates.longitude)).body()

        val dailyWeatherList = mutableListOf<DailyWeather>()

        result.data?.forEach { data ->
            dailyWeatherList.add(
                DailyWeather(
                    datestampLocal = LocalDate.parse(data?.validDate!!),
                    description = data.weather?.description ?: "",
                    iconURL = URLs().getIconURL(data.weather?.icon ?: ""),

                    temperature = data.temp,
                    maxTemperature = data.maxTemp,
                    minTemperature = data.minTemp,
                    pressure = data.pres,
                    humidity = data.rh,
                    uvIndex = data.uv?.toInt() ?: 0,
                    windSpeed = data.windSpd,
                    windDirectionShort = data.windCdir,
                    windDirectionFull = data.windCdirFull,
                    rainfall = data.precip,
                    visibility = data.vis,
                    sunrise = Instant.fromEpochSeconds(data.sunriseTs!!).toLocalDateTime(TimeZone.of(result.timezone)).time,
                    sunset = Instant.fromEpochSeconds(data.sunsetTs!!).toLocalDateTime(TimeZone.of(result.timezone)).time,
                )
            )
        }

        return ForecastDaily(
            lastUpdate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            city = result.cityName,
            longitude = result.lon,
            latitude = result.lat,
            timezone = result.timezone,
            dailyWeather = dailyWeatherList
        )
    }

    override suspend fun getAlerts(mapCoordinates: MapCoordinates): AlertWeather {
        TODO("Not yet implemented")
    }
}