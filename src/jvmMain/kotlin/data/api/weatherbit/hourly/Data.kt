package data.api.weatherbit.hourly


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("app_temp")
    val appTemp: Double?,
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
    @SerialName("dhi")
    val dhi: Double?,
    @SerialName("dni")
    val dni: Double?,
    @SerialName("ghi")
    val ghi: Double?,
    @SerialName("ozone")
    val ozone: Double?,
    @SerialName("pod")
    val pod: String?,
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
    @SerialName("solar_rad")
    val solarRad: Double?,
    @SerialName("temp")
    val temp: Double?,
    @SerialName("timestamp_local")
    val timestampLocal: String?,
    @SerialName("timestamp_utc")
    val timestampUtc: String?,
    @SerialName("ts")
    val ts: Int?,
    @SerialName("uv")
    val uv: Double?,
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