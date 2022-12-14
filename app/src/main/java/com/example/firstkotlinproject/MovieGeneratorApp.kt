package com.example.firstkotlinproject

import android.app.Application
import com.example.firstkotlinproject.data.locale.room.AppDataBase
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.InternalCoroutinesApi


@HiltAndroidApp
class MovieGeneratorApp: Application() {

}