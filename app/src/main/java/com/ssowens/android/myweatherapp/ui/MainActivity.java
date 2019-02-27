package com.ssowens.android.myweatherapp.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ssowens.android.myweatherapp.BuildConfig;
import com.ssowens.android.myweatherapp.R;
import com.ssowens.android.myweatherapp.model.WeatherResponseByCity;
import com.ssowens.android.myweatherapp.service.WeatherApi;

import java.text.DateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
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

    //Images - http://openweathermap.org/img/w/10d.png

    public static String UNITS = "imperial";
    public String AppId = BuildConfig.ApiKey;

    // For Atlanta - Testing ONLY
    public static String lat = "33.935101";
    public static String lon = "-84.360924";

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getCurrentWeather();


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

        if (id == R.id.action_settings) {
            //startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        if (id == R.id.action_map) {
            //openPreferredLocationInMap();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getCurrentWeather() {
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
                        Timber.d("Sheila url %s", url);
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
}
