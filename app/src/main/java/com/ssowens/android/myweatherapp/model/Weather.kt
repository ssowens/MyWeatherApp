package com.ssowens.android.myweatherapp.model

/**
 * Created by Sheila Owens on 2/23/19.
 */


// Data Model for the Weather by City
data class Weather(
        val id: Int,
        val main: String,
        val description: String,
        val icon: String
)

// Data Model for the Response returned from the Weather Api
data class WeatherResponse(
        val results: List<Weather>
)
