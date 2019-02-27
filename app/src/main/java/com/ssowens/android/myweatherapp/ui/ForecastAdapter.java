package com.ssowens.android.myweatherapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ssowens.android.myweatherapp.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * Created by Sheila Owens on 2/26/19.
 */
public class ForecastAdapter extends RecyclerView.Adapter {

    private final Context context;

    final private ForecastAdapterOnClickHandler clickHandler;




    public ForecastAdapter(Context context, ForecastAdapterOnClickHandler clickHandler) {
        this.context = context;
        this.clickHandler = clickHandler;
    }

    public interface ForecastAdapterOnClickHandler {
        void onClick(long date);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_list_item,
                        parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        Glide.with(context)
//                .asBitmap()
//                .load()
//                .into(holder.)
//        holder.
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ForecastViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.weather_icon)
        ImageView weatherImage;
        @BindView(R.id.weather_date)
        TextView weatherDate;
        @BindView(R.id.weather_description)
        TextView description;
        @BindView(R.id.high_temperature)
        TextView highTemp;

        public ForecastViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();

        }
    }
}
