
# Weather Desktop

A weather display application that receives weather via a public API.
The application provides data on temperature, weather, pressure, humidity, UV index, wind speed.

Tech:
 - Compose Desktop
 - Kotlin
 - JavaFX

![Video loading...](https://github.com/GpixelProg/WeatherDesktopApp/blob/master/screenshots/weather.gif?raw=true)

to run, add the object - `Properties` to `src/jvmMain/kotlin/data`

```
package data

object Properties {
    val API_KEY = "YOUR Weatherbit API KEY"
}
```

But before that, get the API key - https://www.weatherbit.io/api/weather-current

You may also encounter build issues due to the use of JavaFX, which will be better implemented in the future.

<div align="center">
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/192108890-200809d1-439c-4e23-90d3-b090cf9a4eea.png" alt="IntelliJ" title="IntelliJ"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/117201156-9a724800-adec-11eb-9a9d-3cd0f67da4bc.png" alt="Java" title="Java"/></code>
	<code><img width="50" src="https://user-images.githubusercontent.com/25181517/185062810-7ee0c3d2-17f2-4a98-9d8a-a9576947692b.png" alt="Kotlin" title="Kotlin"/></code>
</div>
