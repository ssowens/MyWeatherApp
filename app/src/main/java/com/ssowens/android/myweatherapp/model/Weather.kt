package com.ssowens.android.myweatherapp.model

import com.google.gson.annotations.SerializedName

data class Weather(
        @SerializedName("id")
        val id: Int = 0,
        @SerializedName("main")
        val main: String,
        @SerializedName("description")
        var description: String,
        @SerializedName("icon")
        var icon: String
)