package com.ssowens.android.myweatherapp.model

import com.google.gson.annotations.SerializedName

data class Rain(
        @SerializedName("3h")
        var h3: Float = 0.toFloat()
)
