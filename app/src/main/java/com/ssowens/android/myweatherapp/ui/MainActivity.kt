package com.ssowens.android.myweatherapp.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ssowens.android.myweatherapp.BuildConfig
import com.ssowens.android.myweatherapp.R
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        setContentView(R.layout.activity_main)

        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)

        weatherViewModel.fetchCurrentWeather()
        weatherViewModel.currentWeatherLiveData.observe(this, Observer {
            Timber.d("Sheila Made it here")
        })

    }
}