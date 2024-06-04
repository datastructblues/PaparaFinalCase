package com.example.recipeappfinalcase.data.model


import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("imageType")
    val imageType: String,
    @SerializedName("title")
    val title: String
)