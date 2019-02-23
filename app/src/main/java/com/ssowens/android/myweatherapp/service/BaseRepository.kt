package com.ssowens.android.myweatherapp.service

import retrofit2.Response
import timber.log.Timber
import java.io.IOException

/**
 * Created by Sheila Owens on 2/23/19.
 */
open class BaseRepository {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {

        val result: Result<T> = safeApiResult(call, errorMessage)
        var data: T? = null

        when (result) {
            is Result.Success ->
                data = result.data
            is Result.Error -> {
                Timber.d("1.DataRepository %s", "$errorMessage & Exception - ${result.exception}")
            }
        }

        return data

    }

    private suspend fun <T : Any> safeApiResult(
            call: suspend () -> Response<T>,
            errorMessage:
            String
    ): Result<T> {
        val response = call.invoke()
        if (response.isSuccessful) return Result.Success(response.body()!!)

        return Result.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
    }
}