package com.ssowens.android.myweatherapp.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ssowens.android.myweatherapp.BuildConfig;
import com.ssowens.android.myweatherapp.R;
import com.ssowens.android.myweatherapp.model.WeatherResponseByCity;
import com.ssowens.android.myweatherapp.service.WeatherApi;

import java.text.DateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by Sheila Owens on 2/24/19.
 */
public class MainActivity extends AppCompatActivity {

    public static String BASE_URL = "http://api.openweathermap.org/";
    public static String IMAGE_URL = "img/w/";
    public static String PNG_EXT = ".png";
    public static String DEGREE_SYMBOL = "\u00b0";
    public static final String ARG_LAT = "lat";
    public static final String ARG_LON = "lon";
    public static final String LAT_KEY = "mylat";
    public static final String LONG_KEY = "mylong";
    public String savedLat;
    public String savedLon;


    public String UNITS = "imperial";
    public String AppId = BuildConfig.ApiKey;

    // For Atlanta - Testing ONLY
    public String lat = "33.749";
    public String lon = "-71.0583";

    WeatherResponseByCity byCity;

    @BindView(R.id.high_temperature)
    TextView highTemperature;
    @BindView(R.id.low_temperature)
    TextView lowTemperature;
    @BindView(R.id.weather_description)
    TextView weatherDesciption;
    @BindView(R.id.weather_date)
    TextView currentDate;
    @BindView(R.id.weather_icon)
    ImageView weatherIcon;
    @BindView(R.id.current_weather_layout)
    ConstraintLayout main_constraint_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (isOnline()) {
            getCurrentWeather(lat, lon);
            Toast.makeText(getApplicationContext(), "Current Weather displayed",
                    Toast.LENGTH_SHORT).show();

            main_constraint_layout.setOnClickListener(v -> {
                Toast.makeText(getApplicationContext(), "This is the current weather", Toast.LENGTH_SHORT).show();
                Intent weatherDetailIntent = new Intent(MainActivity.this, ForecastActivity.class);
                weatherDetailIntent.putExtra(ARG_LAT, lat);
                weatherDetailIntent.putExtra(ARG_LON, lon);
                startActivity(weatherDetailIntent);

            });
        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connectivity",
                    Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    public void saveLocationToSharedPreferences(String myLat, String myLon) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(LAT_KEY, myLat);
        editor.putString(LONG_KEY, myLon);
        editor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_location, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_city1) {
            // Atlanta
            setTitle(getResources().getString(R.string.action_city1));
            lat = "33.749";
            lon = "-71.0583";
            saveLocationToSharedPreferences(lat, lon);
            getCurrentWeather(lat, lon);
            return true;
        }
        if (id == R.id.action_city2) {
            setTitle(getResources().getString(R.string.action_city2));
            lat = "42.3603";
            lon = "-71.0583";
            saveLocationToSharedPreferences(lat, lon);
            getCurrentWeather(lat, lon);
            return true;
        }
        if (id == R.id.action_city3) {
            //Miama
            setTitle(getResources().getString(R.string.action_city3));
            lat = "25.7743";
            lon = "-80.1937";
            saveLocationToSharedPreferences(lat, lon);
            getCurrentWeather(lat, lon);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getCurrentWeather(String lat, String lon) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherApi service = retrofit.create(WeatherApi.class);
        Call<WeatherResponseByCity> call = service.getCurrentWeatherData(lat, lon, UNITS, AppId);
        call.enqueue(new Callback<WeatherResponseByCity>() {
            @Override
            public void onResponse(Call<WeatherResponseByCity> call, Response<WeatherResponseByCity> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        byCity = response.body();
                        Timber.d("weatherRespBycity %s", response.body().toString());

                        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                        currentDate.setText(currentDateTimeString);
                        weatherDesciption.setText(byCity.getWeather().get(0).main);
                        highTemperature.setText(String.format("%.0f", byCity.getMain().temp) + DEGREE_SYMBOL);
                        lowTemperature.setText(String.format("%.0f", byCity.getMain().temp_min) + DEGREE_SYMBOL);
                        String url =
                                BASE_URL + IMAGE_URL + byCity.getWeather().get(0).getIcon() +
                                        PNG_EXT;
                        Glide
                                .with(getApplicationContext())
                                .load(url)
                                .into(weatherIcon);

                    }
                } else {
                    Timber.d("Returned empty response");
                }
            }

            @Override
            public void onFailure(Call<WeatherResponseByCity> call, Throwable t) {
                Timber.d("Failure - %s", t.getMessage());
            }
        });
    }

    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    @Override
    protected void onResume() {
        super.onResume();
        getLocationToSharedPreferences();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getLocationToSharedPreferences();
    }

    public void getLocationToSharedPreferences() {

        // Get the Lat/Long
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        savedLat = preferences.getString(LAT_KEY, "33.749");
        savedLon = preferences.getString(LONG_KEY, "-71.0583");
    }

}
