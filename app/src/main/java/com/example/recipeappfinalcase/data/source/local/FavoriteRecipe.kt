package com.example.recipeappfinalcase.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_recipes")
data class FavoriteRecipe(
    @PrimaryKey val id: Int,
    val title: String,
    val image: String,
    val summary: String
)