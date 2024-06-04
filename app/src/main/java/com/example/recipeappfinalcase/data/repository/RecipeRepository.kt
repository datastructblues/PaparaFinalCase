package com.example.recipeappfinalcase.data.repository

import com.example.recipeappfinalcase.data.source.network.RecipeNetworkDataSource
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val recipeNetworkDataSource: RecipeNetworkDataSource
){

    suspend fun getRecipes(number: Int, offset: Int) = recipeNetworkDataSource.getRecipes(number, offset)

    suspend fun getMealDetails(id: Int) = recipeNetworkDataSource.getMealDetails(id)
}