package com.ssowens.android.myweatherapp.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Sheila Owens on 2/22/19.
 */
class WeatherData {

    @SerializedName("cod")
    var cod: String? = null

    @SerializedName("message")
    var message: Double? = null

    @SerializedName("cnt")
    var cnt: Int? = null

    @SerializedName("list")
    var list: List<WeatherList>? = null


    data class Weather(
            @SerializedName("weatherId")
            var weatherId: Int? = null,

            @SerializedName("main")
            var main: String? = null,

            @SerializedName("description")
            var description: String? = null,

            @SerializedName("icon")
            var icon: String? = null
    )

    data class Main (

        @SerializedName("temp")
        var temp: Double? = null,

        @SerializedName("temp_min")
        var tempMin: Double? = null,

        @SerializedName("temp_max")
        var tempMax: Double? = null,

        @SerializedName("pressure")
        var pressure: Double? = null,

        @SerializedName("sea_level")
        var seaLevel: Double? = null,

        @SerializedName("grnd_level")
        var grndLevel: Double? = null,

        @SerializedName("humidity")
        var humidity: Int? = null,

        @SerializedName("temp_kf")
        var tempKf: Double? = null

    )

    inner class Snow {

        @SerializedName("3h")
        var threeH:Double? = null

    }

    inner class Wind {

        @SerializedName("speed")
        var speed: Double? = null

        @SerializedName("deg")
        var deg: Double? = null

    }

    inner class WeatherList {

        @SerializedName("dt")
        var dt: Int? = null

        @SerializedName("main")
        var main: WeatherData.Main? = null

        @SerializedName("weather")
        var weather: List<WeatherData.Weather>? = null

        @SerializedName("clouds")
        var clouds: Clouds? = null

        @SerializedName("wind")
        var wind: Wind? = null

        @SerializedName("snow")
        var snow: Snow? = null

        @SerializedName("sys")
        var sys: Sys? = null

        @SerializedName("dt_txt")
        var dtTxt: String? = null
    }

    inner class Clouds {

        @SerializedName("all")
        var all: Int? = null
    }

    inner class Sys {

        @SerializedName("pod")
        var pod: String? = null
    }


}
