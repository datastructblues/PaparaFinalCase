package com.example.recipeappfinalcase.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRecipe(recipe: FavoriteRecipe)

    @Query("SELECT * FROM favorite_recipes WHERE id = :id")
    fun getFavoriteRecipeById(id: Int): FavoriteRecipe?

    @Query("SELECT * FROM favorite_recipes")
    fun getFavoriteRecipes(): Flow<List<FavoriteRecipe>>

    @Query("DELETE FROM favorite_recipes WHERE id = :id")
    suspend fun deleteFavoriteRecipe(id: Int)
}