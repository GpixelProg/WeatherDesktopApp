package data.api.weatherbit.daily


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("app_max_temp")
    val appMaxTemp: Double?,
    @SerialName("app_min_temp")
    val appMinTemp: Double?,
    @SerialName("clouds")
    val clouds: Int?,
    @SerialName("clouds_hi")
    val cloudsHi: Int?,
    @SerialName("clouds_low")
    val cloudsLow: Int?,
    @SerialName("clouds_mid")
    val cloudsMid: Int?,
    @SerialName("datetime")
    val datetime: String?,
    @SerialName("dewpt")
    val dewpt: Double?,
    @SerialName("high_temp")
    val highTemp: Double?,
    @SerialName("low_temp")
    val lowTemp: Double?,
    @SerialName("max_dhi")
    val maxDhi: Double?,
    @SerialName("max_temp")
    val maxTemp: Double?,
    @SerialName("min_temp")
    val minTemp: Double?,
    @SerialName("moon_phase")
    val moonPhase: Double?,
    @SerialName("moon_phase_lunation")
    val moonPhaseLunation: Double?,
    @SerialName("moonrise_ts")
    val moonriseTs: Int?,
    @SerialName("moonset_ts")
    val moonsetTs: Int?,
    @SerialName("ozone")
    val ozone: Double?,
    @SerialName("pop")
    val pop: Int?,
    @SerialName("precip")
    val precip: Double?,
    @SerialName("pres")
    val pres: Double?,
    @SerialName("rh")
    val rh: Double?,
    @SerialName("slp")
    val slp: Double?,
    @SerialName("snow")
    val snow: Int?,
    @SerialName("snow_depth")
    val snowDepth: Int?,
    @SerialName("sunrise_ts")
    val sunriseTs: Long?,
    @SerialName("sunset_ts")
    val sunsetTs: Long?,
    @SerialName("temp")
    val temp: Double?,
    @SerialName("ts")
    val ts: Long?,
    @SerialName("uv")
    val uv: Double?,
    @SerialName("valid_date")
    val validDate: String?,
    @SerialName("vis")
    val vis: Double?,
    @SerialName("weather")
    val weather: Weather?,
    @SerialName("wind_cdir")
    val windCdir: String?,
    @SerialName("wind_cdir_full")
    val windCdirFull: String?,
    @SerialName("wind_dir")
    val windDir: Int?,
    @SerialName("wind_gust_spd")
    val windGustSpd: Double?,
    @SerialName("wind_spd")
    val windSpd: Double?
)