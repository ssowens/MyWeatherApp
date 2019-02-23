package com.ssowens.android.myweatherapp.service

import com.ssowens.android.myweatherapp.model.Weather
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Sheila Owens on 2/23/19.
 */
// A retrofit Network Interface for the Api
interface WeatherApi {
  @GET("weather/{id}")
  fun getWeatherByCity(@Path("id") id: Int): Deferred<Response<Weather>>
}