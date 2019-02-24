package com.ssowens.android.myweatherapp.ui;

import android.os.Bundle;

import com.ssowens.android.myweatherapp.BuildConfig;
import com.ssowens.android.myweatherapp.R;

import androidx.appcompat.app.AppCompatActivity;
import timber.log.Timber;

/**
 * Created by Sheila Owens on 2/24/19.
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        setContentView(R.layout.activity_main);

        //        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
//
//        weatherViewModel.fetchCurrentWeather()
//        weatherViewModel.currentWeatherLiveData.observe(this, Observer {
//            Timber.d("Sheila Made it here")
//        })
    }
}
