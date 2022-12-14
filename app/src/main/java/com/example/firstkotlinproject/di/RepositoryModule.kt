package com.example.firstkotlinproject.di

import com.example.firstkotlinproject.data.MovieRepository
import com.example.firstkotlinproject.data.locale.LocaleDataSource
import com.example.firstkotlinproject.data.remote.RemoteDataSource
import com.example.firstkotlinproject.repository.MovieRepositoryImplNew
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideRepository(
        localData: LocaleDataSource,
        remoteData: RemoteDataSource
        ): MovieRepository{
        return MovieRepositoryImplNew(localData, remoteData)
    }

}