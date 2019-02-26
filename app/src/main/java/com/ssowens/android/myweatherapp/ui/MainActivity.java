package com.ssowens.android.myweatherapp.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.ssowens.android.myweatherapp.BuildConfig;
import com.ssowens.android.myweatherapp.R;
import com.ssowens.android.myweatherapp.model.WeatherResponseByCity;
import com.ssowens.android.myweatherapp.service.WeatherApi;

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

    public static String BaseUrl = "http://api.openweathermap.org/";

    //TODO FOR TESTING ONLY
    public static String AppId = "2e65127e909e178d0af311a81f39948c";
    public String apiKey = BuildConfig.ApiKey;
    public static String lat = "35";
    public static String lon = "139";
    WeatherResponseByCity byCity;

    @BindView(R.id.high_temperature)
    TextView highTemperature;
    @BindView(R.id.low_temperature)
    TextView lowTemperature;
    @BindView(R.id.weather_description)
    TextView weatherDesciption;

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
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherApi service = retrofit.create(WeatherApi.class);
        Call<WeatherResponseByCity> call = service.getCurrentWeatherData(lat, lon, AppId);
        call.enqueue(new Callback<WeatherResponseByCity>() {
            @Override
            public void onResponse(Call<WeatherResponseByCity> call, Response<WeatherResponseByCity> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        byCity = response.body();
                        Timber.d("weatherRespBycity %s", response.body().toString());
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
