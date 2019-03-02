package com.ssowens.android.myweatherapp.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Sheila Owens on 2/25/19.
 */
data class WeatherResponseByCity (
    @SerializedName("coord")
    var coord: Coord,
    @SerializedName("sys")
    var sys: Sys,
    @SerializedName("weather")
    var weather: List<Weather> = ArrayList(),
    @SerializedName("main")
    var main: Main,
    @SerializedName("wind")
    var wind: Wind,
    @SerializedName("rain")
    var rain: Rain,
    @SerializedName("clouds")
    var clouds: Clouds,
    @SerializedName("dt")
    var dt: Float = 0.toFloat(),
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String,
    @SerializedName("cod")
    var cod: Float = 0.toFloat()
)

