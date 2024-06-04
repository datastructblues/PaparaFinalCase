package com.example.recipeappfinalcase.data

import com.example.recipeappfinalcase.data.source.network.NetworkState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

fun <T> apiFlow(
    call: suspend () -> Response<T>
): Flow<NetworkState<T>> = flow {
    emit(NetworkState.Loading)
    try {
        val networkCall = call()
        networkCall.let { c ->
            if (c.isSuccessful) {
                val body = c.body()
                if (body != null) {
                    emit(NetworkState.Success(body))
                } else {
                    emit(NetworkState.Error("Response body is null"))
                }
            } else {
                emit(NetworkState.Error(c.errorBody()?.string() ?: "An error occurred"))
            }
        }
    } catch (e: Exception) {
        emit(NetworkState.Error(e.message ?: "An error occurred"))
    }
}.flowOn(Dispatchers.IO)