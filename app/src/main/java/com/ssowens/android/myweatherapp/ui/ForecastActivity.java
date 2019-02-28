package com.ssowens.android.myweatherapp.ui;

import android.os.Bundle;

import com.ssowens.android.myweatherapp.BuildConfig;
import com.ssowens.android.myweatherapp.R;
import com.ssowens.android.myweatherapp.model.WeatherForecast;
import com.ssowens.android.myweatherapp.model.WeatherResponseByCity;
import com.ssowens.android.myweatherapp.service.WeatherApi;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

import static com.ssowens.android.myweatherapp.ui.MainActivity.BASE_URL;

public class ForecastActivity extends AppCompatActivity implements
        ForecastAdapter.ForecastAdapterOnClickHandler {

    // For Atlanta - Testing ONLY
    public String lat = "33.935101";
    public String lon = "-84.360924";
    public String AppId = BuildConfig.ApiKey;
    WeatherResponseByCity byCity;
    WeatherForecast weatherForecast;
    private ForecastAdapter.ForecastAdapterOnClickHandler clickHandler;

    public ForecastAdapter forecastAdapter;
    private List<WeatherForecast.WeatherList> weatherList = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Timber.d("Sheila in second activity");
        recyclerView = findViewById(R.id.recyclerview_forecast);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        getWeatherForcast(clickHandler);

    }

    public void getWeatherForcast(ForecastAdapter.ForecastAdapterOnClickHandler clickHandler) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherApi service = retrofit.create(WeatherApi.class);
        Call<WeatherForecast> call = service.getForecast(lat, lon, "imperial", AppId);
        call.enqueue(new Callback<WeatherForecast>() {
            @Override
            public void onResponse(Call<WeatherForecast> call, Response<WeatherForecast> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        weatherForecast = response.body();
                        Timber.d("Sheila Detail - WeatherForecast %s",
                                response.body().toString());

                        convertData(weatherForecast);
                        forecastAdapter = new ForecastAdapter(clickHandler, weatherList);
                        recyclerView.setAdapter(forecastAdapter);

                    }
                } else {
                    Timber.d("Returned empty response");
                }
            }

            @Override
            public void onFailure(Call<WeatherForecast> call, Throwable t) {
                Timber.d("Failure - %s", t.getMessage());
            }
        });
    }

    private void convertData(WeatherForecast weatherForecast) {
        weatherList.addAll(weatherForecast.getList());
    }

    @Override
    public void onClick(long date) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
