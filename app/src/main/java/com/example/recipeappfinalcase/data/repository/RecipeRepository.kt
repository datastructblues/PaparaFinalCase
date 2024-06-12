package com.example.recipeappfinalcase.data.repository

import com.example.recipeappfinalcase.data.model.RecipeResponse
import com.example.recipeappfinalcase.data.model.detail.DetailResponse
import com.example.recipeappfinalcase.data.source.local.FavoriteRecipe
import com.example.recipeappfinalcase.data.source.network.NetworkState
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    suspend fun getRecipes(
        number: Int,
        offset: Int,
        query: String?=null,
        cuisine: String?=null,
        diet: String?=null,
        type: String?=null
    ) : Flow<NetworkState<RecipeResponse>>

    suspend fun getRecipeDetails(id: Int) : Flow<NetworkState<DetailResponse>>

    suspend fun getFavoriteRecipes() : Flow<List<FavoriteRecipe>>

    suspend fun insertFavoriteRecipe(recipe: FavoriteRecipe)

    suspend fun deleteFavoriteRecipe(id: Int)

    suspend fun getLocalRecipeDetails(id: Int): FavoriteRecipe?
}
