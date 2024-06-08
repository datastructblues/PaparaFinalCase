package com.example.recipeappfinalcase.data.repository

import com.example.recipeappfinalcase.data.model.RecipeResponse
import com.example.recipeappfinalcase.data.model.detail.DetailResponse
import com.example.recipeappfinalcase.data.source.network.NetworkDataSource
import com.example.recipeappfinalcase.data.source.network.NetworkState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource
) : RecipeRepository {
    override suspend fun getRecipes(number: Int, offset: Int): Flow<NetworkState<RecipeResponse>> {
       val recipeResponse = networkDataSource.getRecipes(number, offset)
        recipeResponse.collect { value ->
            when (value) {
                is NetworkState.Success -> {
                    // ignored
                }
                else -> {
                    // ignored
                }
            }
        }
        return recipeResponse
    }

    override suspend fun getRecipeDetails(id: Int): Flow<NetworkState<DetailResponse>> {
        return networkDataSource.getMealDetails(id)
    }
}