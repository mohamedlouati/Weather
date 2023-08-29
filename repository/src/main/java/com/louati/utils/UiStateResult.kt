package com.louati.utils

sealed class UiStateResult<out T : Any>{
    data class LoadingState<out T : Any>(val data: T? = null) : UiStateResult<T>()
    object EmptyState : UiStateResult<Nothing>()
    object UnknownState : UiStateResult<Nothing>()
    data class DataState<out T : Any>(val data: T?) : UiStateResult<T>()
    object ServerErrorState : UiStateResult<Nothing>()
    object NetworkErrorState: UiStateResult<Nothing>()
}
