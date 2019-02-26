package com.ssowens.android.myweatherapp.model;

import com.google.gson.annotations.SerializedName;

public class Clouds {
    @SerializedName("all")
    public float all;

    public float getAll() {
        return all;
    }

    public void setAll(float all) {
        this.all = all;
    }

    @Override
    public String toString() {
        return "Clouds{" +
                "all=" + all +
                '}';
    }
}
