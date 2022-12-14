package com.example.firstkotlinproject.di

import android.content.Context
import com.example.firstkotlinproject.data.locale.room.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun getDatabase(@ApplicationContext context: Context): AppDataBase = AppDataBase.getInstance(context)

}