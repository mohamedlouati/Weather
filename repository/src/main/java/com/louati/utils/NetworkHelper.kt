package com.louati.utils

import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.TimeoutCancellationException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

object NetworkHelper {
    fun <T : Any> handleThrowable(exception: Exception): Resource<T> {
        return when (exception) {
            is TimeoutCancellationException -> {
                Resource.Failure(isNetworkError = true, errorCode = null, errorBody = null)
            }

            is SocketTimeoutException -> {
                Resource.Failure(isNetworkError = true, errorCode = null, errorBody = null)
            }

            is IOException -> {
                Resource.Failure(isNetworkError = true, errorCode = null, errorBody = null)
            }

            is HttpException -> {
                Resource.Failure(
                    isNetworkError = false,
                    errorCode = exception.code(),
                    errorBody = exception.response()?.errorBody()
                )
            }

            is JsonSyntaxException -> {
                Resource.Failure(isNetworkError = false, errorCode = null, errorBody = null)
            }

            else -> {
                Resource.Failure(isNetworkError = false, errorCode = null, errorBody = null)
            }
        }
    }
}