package com.ssowens.android.myweatherapp.service;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ssowens.android.myweatherapp.model.WeatherForecast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.ssowens.android.myweatherapp.ui.ForecastActivity.IMPERIAL;

// Singleton Pattern
public class WeatherDataRepository {

    private WeatherApi weatherApi;
    private static WeatherDataRepository instance;

    public static WeatherDataRepository getInstance() {
        if(instance == null){
            instance = new WeatherDataRepository();
        }
        return instance;
    }

//    public WeatherDataRepository() {
//        WeatherApi weatherApi = RetrofitRequest.createService(WeatherApi.class);
//    }

//    public LiveData<WeatherForecast> getHeadLine(String country,
//                                                 String key) {
//        final MutableLiveData<WeatherForecast> data = new MutableLiveData<>();
//        weatherApi.getForecast(lat, lon, IMPERIAL, AppId)
//                .enqueue(new Callback<WeatherForecast>() {
//                    @Override
//                    public void onResponse(Call<WeatherForecast> call, Response<WeatherForecast> response) {
//                        if (response.isSuccessful()) {
//                            data.setValue(response.body());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<WeatherForecast> call, Throwable t) {
//                        Timber.d("Failure - %s", t.getMessage());
//                        data.setValue(null);
//                    }
//                });
//        return data;
//    }


}
