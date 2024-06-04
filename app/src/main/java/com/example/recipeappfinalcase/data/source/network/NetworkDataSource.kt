package com.example.recipeappfinalcase.data.source.network

interface NetworkDataSource {

    suspend fun getRecipes(number: Int, offset: Int): Any

    suspend fun getMealDetails(id: Int): Any



}