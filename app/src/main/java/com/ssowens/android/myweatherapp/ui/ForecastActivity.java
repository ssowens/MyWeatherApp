package com.ssowens.android.myweatherapp.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ssowens.android.myweatherapp.BuildConfig;
import com.ssowens.android.myweatherapp.R;
import com.ssowens.android.myweatherapp.model.WeatherForecast;
import com.ssowens.android.myweatherapp.model.WeatherResponseByCity;
import com.ssowens.android.myweatherapp.service.WeatherApi;
import com.ssowens.android.myweatherapp.viewmodels.ForecastActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

import static com.ssowens.android.myweatherapp.ui.MainActivity.ARG_LAT;
import static com.ssowens.android.myweatherapp.ui.MainActivity.ARG_LON;
import static com.ssowens.android.myweatherapp.ui.MainActivity.ATL_LAT;
import static com.ssowens.android.myweatherapp.ui.MainActivity.ATL_LON;
import static com.ssowens.android.myweatherapp.ui.MainActivity.BASE_URL;
import static com.ssowens.android.myweatherapp.ui.MainActivity.KEY_LAT;
import static com.ssowens.android.myweatherapp.ui.MainActivity.KEY_LON;

public class ForecastActivity extends AppCompatActivity implements
        ForecastAdapter.ForecastAdapterOnClickHandler {

    public String lat = "";
    public String lon = "";
    public String AppId = BuildConfig.ApiKey;
    public static String IMPERIAL = "imperial";
    WeatherResponseByCity byCity;
    WeatherForecast weatherForecast;
    private ForecastAdapter.ForecastAdapterOnClickHandler clickHandler;

    public ForecastAdapter forecastAdapter;
    private List<WeatherForecast.WeatherList> weatherList = new ArrayList<>();
    private List<WeatherForecast.WeatherList> myTestList = new ArrayList<>();

    @BindView(R.id.recyclerview_forecast)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private ForecastActivityViewModel forecastActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        forecastActivityViewModel = ViewModelProviders.of(this).get(ForecastActivityViewModel.class);

        if (getIntent() != null) {
            lat = getIntent().getStringExtra(ARG_LAT);
            lon = getIntent().getStringExtra(ARG_LON);
        } else if (savedInstanceState != null) {
            lat = savedInstanceState.getString(KEY_LAT, ATL_LAT);
            lon = savedInstanceState.getString(KEY_LON, ATL_LON);
        } else loadLatLonSharedPreferences();

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        getWeatherForcast(lat, lon, clickHandler);
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(KEY_LAT, lat);
        savedInstanceState.putString(KEY_LON, lon);
    }

    public void getWeatherForcast(String lat, String lon, ForecastAdapter.ForecastAdapterOnClickHandler clickHandler) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherApi service = retrofit.create(WeatherApi.class);
        Call<WeatherForecast> call = service.getForecast(lat, lon, IMPERIAL, AppId);
        call.enqueue(new Callback<WeatherForecast>() {
            @Override
            public void onResponse(Call<WeatherForecast> call, Response<WeatherForecast> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        weatherForecast = response.body();
                        convertData(weatherForecast);
                        forecastAdapter = new ForecastAdapter(clickHandler, weatherList);
                        recyclerView.setAdapter(forecastAdapter);
                        forecastAdapter.notifyDataSetChanged();
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
        for (int i = 0; i < weatherList.size() - 1; i++) {
            Timber.i("This is the date %s", weatherList.get(i).getDtTxt());
            if (!weatherList.get(i).getDtTxt().substring(0, 10).equals(weatherList.get(i + 1).getDtTxt().substring(0, 10))) {
                myTestList.add(weatherList.get(i));
            }
        }
        Timber.i("Size of myTestList %s", myTestList.size());
        weatherList.clear();
        weatherList.addAll(myTestList);
    }

    @Override
    public void onClick(long date) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Get the Lat/Long
        loadLatLonSharedPreferences();
    }

    private void loadLatLonSharedPreferences() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        lat = preferences.getString(KEY_LAT, ATL_LAT);
        lon = preferences.getString(KEY_LON, ATL_LON);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveLatLonSharedPreferences();
    }

    @Override
    public void onBackPressed() {
        saveLatLonSharedPreferences();
        super.onBackPressed();
    }

    private void saveLatLonSharedPreferences() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_LAT, lat);
        editor.putString(KEY_LON, lon);
        editor.apply();
    }
}
