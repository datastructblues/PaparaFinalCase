package com.example.recipeappfinalcase.data.source.network

import com.example.recipeappfinalcase.BuildConfig
import com.example.recipeappfinalcase.data.model.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeService {

    @GET("recipes/complexSearch")
    suspend fun getRecipes(
        @Query("number") number: Int = 30,
        @Query("offset") offset: Int = 0,
        @Query("query") query: String? = null,
        @Query("cuisine") cuisine: String? = null,
        @Query("diet") diet: String? = null,
        @Query("type") type: String? = null,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): Response<RecipeResponse>

}