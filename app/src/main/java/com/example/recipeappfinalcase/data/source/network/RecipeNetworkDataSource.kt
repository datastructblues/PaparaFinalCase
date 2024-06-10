package com.example.recipeappfinalcase.data.source.network

import com.example.recipeappfinalcase.data.apiFlow
import com.example.recipeappfinalcase.data.model.RecipeResponse
import com.example.recipeappfinalcase.data.model.detail.DetailResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RecipeNetworkDataSource @Inject constructor(
    private val recipeService: RecipeService
) : NetworkDataSource {

    override suspend fun getRecipes(
        number: Int,
        offset: Int,
        query: String?,
        cuisine: String?,
        diet: String?,
        type: String?,
    ): Flow<NetworkState<RecipeResponse>> =
        apiFlow {
            recipeService.getRecipes(number, offset, query, cuisine, diet, type)
        }


    override suspend fun getMealDetails(id: Int): Flow<NetworkState<DetailResponse>> = apiFlow {
        recipeService.getMealDetails(id)

    }
}