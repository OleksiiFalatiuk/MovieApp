package com.example.firstkotlinproject.workmanager

import android.content.Context
import android.util.Log
import androidx.work.*
import com.example.firstkotlinproject.data.locale.LocaleDataSource
import com.example.firstkotlinproject.data.remote.RemoteDataSource
import java.util.concurrent.TimeUnit

class MyWorkDetails(context: Context,
                    workerParams: WorkerParameters,
                    private val localData: LocaleDataSource,
                    private val remoteData: RemoteDataSource
): CoroutineWorker(context, workerParams) {


    override suspend fun doWork():Result {
        val movieForDetailsId = inputData.getInt("MOVIE_ID",1)
        return try {
            try {
                Log.d("MyWorker", "Run work manager")
                val movieFromApi = remoteData.loadMovie(movieForDetailsId)
                localData.insertMovieDetails(listOf(movieFromApi))
                Result.success()
            }catch (e: Exception) {
                Log.d("MyWorker", "exception in doWork ${e.message}")
                Result.failure()
            }
        }catch (e: Throwable){
            Log.d("MyWorker", "exception in doWork ${e.message}")
            Result.failure()
        }
    }
}