package com.ssowens.android.myweatherapp.model

import com.google.gson.annotations.SerializedName

data class Main (
    @SerializedName("temp")
    var temp: Float = 0.toFloat(),
    @SerializedName("humidity")
    var humidity: Float = 0.toFloat(),
    @SerializedName("pressure")
    var pressure: Float = 0.toFloat(),
    @SerializedName("temp_min")
    var temp_min: Float = 0.toFloat(),
    @SerializedName("temp_max")
    var temp_max: Float = 0.toFloat()
)
