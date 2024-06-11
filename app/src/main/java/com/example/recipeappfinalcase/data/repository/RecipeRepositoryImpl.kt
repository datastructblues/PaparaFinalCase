package com.example.recipeappfinalcase.data.repository

import com.example.recipeappfinalcase.data.model.RecipeResponse
import com.example.recipeappfinalcase.data.model.detail.DetailResponse
import com.example.recipeappfinalcase.data.source.local.FavoriteRecipe
import com.example.recipeappfinalcase.data.source.local.RecipeDao
import com.example.recipeappfinalcase.data.source.network.NetworkDataSource
import com.example.recipeappfinalcase.data.source.network.NetworkState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: RecipeDao,
) : RecipeRepository {

    override suspend fun getRecipes(
        number: Int,
        offset: Int,
        query: String?,
        cuisine: String?,
        diet: String?,
        type: String?
    ): Flow<NetworkState<RecipeResponse>> {
        val recipeResponse = networkDataSource.getRecipes(number, offset, query, cuisine, diet, type)
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

    override suspend fun getFavoriteRecipes(): Flow<List<FavoriteRecipe>> {
        return localDataSource.getFavoriteRecipes()
    }

    override suspend fun insertFavoriteRecipe(recipe: FavoriteRecipe) {
        localDataSource.insertFavoriteRecipe(recipe)
    }

    override suspend fun deleteFavoriteRecipe(id: Int) {
        localDataSource.deleteFavoriteRecipe(id)
    }
}