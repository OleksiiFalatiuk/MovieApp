package com.example.firstkotlinproject.di

import com.example.firstkotlinproject.data.locale.LocaleDataSource
import com.example.firstkotlinproject.data.locale.room.AppDataBase
import com.example.firstkotlinproject.data.locale.room.RoomData
import com.example.firstkotlinproject.data.remote.RemoteDataSource
import com.example.firstkotlinproject.data.remote.retrofit.MovieApiService
import com.example.firstkotlinproject.data.remote.retrofit.RetrofitDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(appDb: AppDataBase): LocaleDataSource{
        return RoomData(appDb)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(api: MovieApiService): RemoteDataSource{
        return RetrofitDataSource(api)
    }

}