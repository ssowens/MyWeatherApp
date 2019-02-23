package com.ssowens.android.myweatherapp.service

import com.ssowens.android.myweatherapp.model.Weather

/**
 * Created by Sheila Owens on 2/23/19.
 */
class WeatherRespository(private val api: WeatherApi) : BaseRepository() {

    suspend fun getWeatherDays(): Weather? {

        //safeApiCall is defined in BaseRepository.kt (https://gist.github.com/navi25/67176730f5595b3f1fb5095062a92f15)
        val weatherResponse = safeApiCall(
                call = { api.getWeatherByCity(300).await() },
                errorMessage = "Error Fetching Weather by City"
        )

        return weatherResponse

    }
}
