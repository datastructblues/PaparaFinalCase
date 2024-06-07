package com.example.recipeappfinalcase.di

import com.example.recipeappfinalcase.data.source.network.NetworkDataSource
import com.example.recipeappfinalcase.data.source.network.RecipeNetworkDataSource
import com.example.recipeappfinalcase.data.source.network.RecipeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    inline fun <reified T> provideRetrofitService(okHttpClient: OkHttpClient, baseUrl: String): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(T::class.java)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(recipeService: RecipeService): NetworkDataSource {
        return RecipeNetworkDataSource(recipeService)
    }

}