package com.example.recipeappfinalcase.data.repository

import com.example.recipeappfinalcase.data.model.RecipeResponse
import com.example.recipeappfinalcase.data.model.detail.DetailResponse
import com.example.recipeappfinalcase.data.source.network.NetworkState
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    suspend fun getRecipes(number: Int, offset: Int) : Flow<NetworkState<RecipeResponse>>
    suspend fun getRecipeDetails(id: Int) : Flow<NetworkState<DetailResponse>>
}