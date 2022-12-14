package com.example.firstkotlinproject.di

import com.example.firstkotlinproject.data.remote.retrofit.MovieApiService
import com.example.firstkotlinproject.provider.NetworkModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Singleton
    @Provides
    fun retrofitClient(): MovieApiService {
        return NetworkModule().api
    }

}