package com.ssowens.android.myweatherapp.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ssowens.android.myweatherapp.model.Weather
import com.ssowens.android.myweatherapp.service.ApiFactory
import com.ssowens.android.myweatherapp.service.WeatherRespository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Created by Sheila Owens on 2/23/19.
 */
class WeatherViewModel: ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val respository : WeatherRespository = WeatherRespository(ApiFactory.weatherApi)

    val currentWeatherLiveData = MutableLiveData<Weather>()

    fun fetchCurrentWeather() {
        scope.launch {
            val currentWeather = respository.getWeatherDays()
            currentWeatherLiveData.postValue(currentWeather)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}