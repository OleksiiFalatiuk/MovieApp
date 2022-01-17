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


//    fun PeriodicWorkRequest(movieId: Int): PeriodicWorkRequest {
//        val inputData = Data.Builder()
//            .putInt("MOVIE_ID", movieId)
//            .build()
//        val constraint = Constraints.Builder()
//            .setRequiredNetworkType(NetworkType.CONNECTED)
//            .setRequiresCharging(true)
//            .build()
//        return PeriodicWorkRequest.Builder(MyWork::class.java,8, TimeUnit.HOURS)
//            .setInputData(inputData)
//            .setConstraints(constraint)
//            .build()
//    }


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