package com.ssowens.android.myweatherapp.model

import com.google.gson.annotations.SerializedName

data class Sys (
    @SerializedName("country")
    var country: String,
    @SerializedName("sunrise")
    var sunrise: Long = 0,
    @SerializedName("sunset")
    var sunset: Long = 0
)
