package com.louati.utils

import okhttp3.ResponseBody

sealed class Resource<out T> {

    data class Success<out T>(val value: T): Resource<T>()

    data class Error<out T>(
        val isDataEmpty: Boolean = true
    ): Resource<T>()

    data class Failure(
        val isNetworkError: Boolean = false,
        val errorCode: Int? = null,
        val errorBody: ResponseBody? = null
    ): Resource<Nothing>()
}