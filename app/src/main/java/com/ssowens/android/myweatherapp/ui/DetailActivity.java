package com.ssowens.android.myweatherapp.ui;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.ssowens.android.myweatherapp.R;
import com.ssowens.android.myweatherapp.service.WeatherApi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ssowens.android.myweatherapp.ui.MainActivity.BASE_URL;

/**
 * Created by Sheila Owens on 2/26/19.
 */
public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview_forecast)
    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_detail);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

    }

    public void getWeatherForcast() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherApi service = retrofit.create(WeatherApi.class);

    }
}
