package data.api.weatherbit

import data.Properties.API_KEY

class URLs {
    companion object {
        const val BASE_URL = "https://api.weatherbit.io/v2.0/"
        const val CURRENT_WEATHER = "current"
        const val FORECAST_DAILY = "forecast/daily"
        const val FORECAST_HOURLY = "forecast/hourly"
        const val ALERTS = "alerts"
    }

    fun getCurrentWeatherURL(latitude: Double, longitude: Double): String {
        return "$BASE_URL$CURRENT_WEATHER?lat=$latitude&lon=$longitude&key=$API_KEY"
    }

    fun getForecastDailyURL(latitude: Double, longitude: Double): String {
        return "$BASE_URL$FORECAST_DAILY?lat=$latitude&lon=$longitude&key=$API_KEY"
    }

    fun getForecastHourlyURL(latitude: Double, longitude: Double): String {
        return "$BASE_URL$FORECAST_HOURLY?lat=$latitude&lon=$longitude&key=$API_KEY&hours=24"
    }

    fun getAlertsURL(latitude: Double, longitude: Double): String {
        return "$BASE_URL$ALERTS?lat=$latitude&lon=$longitude&key=$API_KEY"
    }

    fun getIconURL(iconCode: String): String {
        return "https://www.weatherbit.io/static/img/icons/$iconCode.png"
    }
}