package com.ssowens.android.myweatherapp.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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

//        APIManager.getApiService().getWeatherInfo(stringLatitude,
//                stringLongitude,
//                "10",
//                API_KEY,
//                callback);

//        WeatherViewModel weatherViewModel =
//                ViewModelProviders.of(this).get(WeatherViewModel.class);

//        weatherViewModel.fetchCurrentWeather();
//        weatherViewModel.currentWeatherLiveData.observe(this, Observer {
//            Timber.d("Sheila Made it here")
//        })
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
}
