package com.ssowens.android.myweatherapp.model

import com.google.gson.annotations.SerializedName

data class Clouds (
    @SerializedName("all")
    var all: Float = 0.toFloat()
)
