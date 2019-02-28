package com.ssowens.android.myweatherapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ssowens.android.myweatherapp.databinding.ForecastListItemBinding;
import com.ssowens.android.myweatherapp.model.WeatherForecast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Sheila Owens on 2/26/19.
 */
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    final private ForecastAdapterOnClickHandler clickHandler;
    private List<WeatherForecast.WeatherList> weatherList;
    private WeatherForecast weatherForecast;

    public ForecastAdapter(ForecastAdapterOnClickHandler clickHandler,
                           WeatherForecast weatherForecast) {
        this.clickHandler = clickHandler;
        this.weatherForecast= weatherForecast;
    }

    public interface ForecastAdapterOnClickHandler {
        void onClick(long date);
    }


    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ForecastListItemBinding forecastListItemBinding =
                ForecastListItemBinding.inflate(layoutInflater, parent, false);
        return new ForecastViewHolder(forecastListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
       holder.binding.setModel(weatherForecast);
    }

    @Override
    public int getItemCount() {
        return weatherForecast.getList().size();
    }

    class ForecastViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        private ForecastListItemBinding binding;

        ForecastViewHolder(ForecastListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();

        }
    }
}
