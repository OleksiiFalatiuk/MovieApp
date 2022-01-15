package com.example.firstkotlinproject.workmanager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.firstkotlinproject.data.locale.LocaleDataSource
import com.example.firstkotlinproject.data.remote.RemoteDataSource


class MyWork(context: Context,
             workerParams: WorkerParameters,
             private val localData: LocaleDataSource,
             private val remoteData: RemoteDataSource
): CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            try {
                Log.d("MyWorker", "Run work manager")
                val movieFromApi = remoteData.loadMovies()
                localData.insertMovies(movieFromApi)
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