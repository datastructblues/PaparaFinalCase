package com.example.recipeappfinalcase.di

import com.example.recipeappfinalcase.BuildConfig
import com.example.recipeappfinalcase.data.source.network.RecipeService
import com.example.recipeappfinalcase.di.NetworkModule.provideRetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipeServiceModule {

    @Provides
    @Singleton
    fun provideRecipeService(okHttpClient: OkHttpClient): RecipeService {
        return provideRetrofitService(okHttpClient, BuildConfig.BASE_URL)
    }
}