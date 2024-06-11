package com.example.recipeappfinalcase.di

import android.content.Context
import androidx.room.Room
import com.example.recipeappfinalcase.data.source.local.RecipeDao
import com.example.recipeappfinalcase.data.source.local.RecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabasesModule {

    @Provides
    @Singleton
    fun provideCharactersDao(database: RecipeDatabase): RecipeDao {
        return database.recipeDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): RecipeDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            RecipeDatabase::class.java,
            "characters_database.db"
        ).build()
    }
}