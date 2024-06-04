package com.example.recipeappfinalcase.data.source.network

sealed class NetworkState<out T> {
    data object Loading : NetworkState<Nothing>()
    data class Success<out T>(val data: T) : NetworkState<T>()
    data class Error(val message: String) : NetworkState<Nothing>()
}