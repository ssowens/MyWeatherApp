package com.ssowens.android.myweatherapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ssowens.android.myweatherapp.model.WeatherForecast;

import java.util.List;

public class ForecastActivityViewModel extends ViewModel {

    private MutableLiveData<List<WeatherForecast.WeatherList>> weatherData;
    public LiveData<List<WeatherForecast.WeatherList>> getWeatherData;

}
