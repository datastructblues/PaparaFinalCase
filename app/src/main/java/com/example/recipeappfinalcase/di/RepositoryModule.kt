package com.example.recipeappfinalcase.di

import com.example.recipeappfinalcase.data.repository.RecipeRepository
import com.example.recipeappfinalcase.data.repository.RecipeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindRepository(repository: RecipeRepositoryImpl): RecipeRepository
}