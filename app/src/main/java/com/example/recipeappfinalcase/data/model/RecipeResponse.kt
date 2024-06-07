package com.example.recipeappfinalcase.data.model


import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("number")
    val number: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("results")
    val recipeList: List<Recipe>,
    @SerializedName("totalResults")
    val totalResults: Int
)