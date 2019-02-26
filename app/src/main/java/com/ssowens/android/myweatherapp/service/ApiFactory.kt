package com.ssowens.android.myweatherapp.service

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.ssowens.android.myweatherapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Sheila Owens on 2/22/19.
 */

object ApiFactory {

    val apiKey = BuildConfig.ApiKey
    val WEATHER_BASE_URL = "http://api.openweathermap.org/data/2.5/"
    // City api.openweathermap.org/data/2.5/weather?q={city name}


    // Create Auth Interceptor to add api_key query in front of all the requests.
    private val authInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url()
                .newBuilder()
                .addQueryParameter("api_key", apiKey)
                .build()

        val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()

        chain.proceed(newRequest)
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Create OkhttpClient and add the API key for building http request url
    // Not logging the authkey if not debug
    private val weatherClient =
            if (BuildConfig.DEBUG) {
                OkHttpClient().newBuilder()
                        .addInterceptor(authInterceptor)
                        .addInterceptor(loggingInterceptor)
                        .build()
            } else {
                OkHttpClient().newBuilder()
                        .addInterceptor(loggingInterceptor)
                        .addInterceptor(authInterceptor)
                        .build()
            }


    // Create the HTTP request builder using Retrofit
    fun retrofit(): Retrofit = Retrofit.Builder()
            .client(weatherClient)
            .baseUrl(WEATHER_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

}
