package com.ssowens.android.myweatherapp.service

/**
 * Created by Sheila Owens on 2/23/19.
 */
sealed class Result<out T: Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}