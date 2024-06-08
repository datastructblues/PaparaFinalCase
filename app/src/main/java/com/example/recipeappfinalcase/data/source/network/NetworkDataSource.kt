package com.example.recipeappfinalcase.data.source.network

import com.example.recipeappfinalcase.data.model.RecipeResponse
import com.example.recipeappfinalcase.data.model.detail.DetailResponse
import kotlinx.coroutines.flow.Flow

interface NetworkDataSource {

    suspend fun getRecipes(number: Int, offset: Int): Flow<NetworkState<RecipeResponse>>

    suspend fun getMealDetails(id: Int): Flow<NetworkState<DetailResponse>>



}