package com.example.firstkotlinproject

import android.app.Application
import com.example.firstkotlinproject.data.locale.room.AppDataBase
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class MovieGeneratorApp: Application() {
    companion object{
        lateinit var appData: AppDataBase
    }

    override fun onCreate() {
        super.onCreate()

        appData = AppDataBase.getInstance(this)
    }

}