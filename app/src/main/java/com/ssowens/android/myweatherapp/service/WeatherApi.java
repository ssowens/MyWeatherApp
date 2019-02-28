package com.ssowens.android.myweatherapp.service;

import com.ssowens.android.myweatherapp.model.WeatherForecast;
import com.ssowens.android.myweatherapp.model.WeatherResponseByCity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Sheila Owens on 2/25/19.
 */
public interface WeatherApi {

    @GET("data/2.5/weather?")
    Call<WeatherResponseByCity> getCurrentWeatherData(
            @Query("lat") String lat,
            @Query("lon") String lon,
            @Query("units") String units,
            @Query("APPID") String app_id
    );

    @GET("data/2.5/weather?")
    Call<WeatherResponseByCity> getCurrentWeatherById(
            @Query("id") String id,
            @Query("units") String units,
            @Query("APPID") String app_id
    );

    @GET("data/2.5/forecast?")
    Call<WeatherForecast> getForecast(
            @Query("lat") String lat,
            @Query("lon") String lon,
            @Query("units") String units,
            @Query("APPID") String app_id
    );
}
