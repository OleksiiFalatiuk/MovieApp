package com.example.firstkotlinproject.workmanager

import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import java.util.concurrent.TimeUnit


class DetailsWorkRequest {

    fun PeriodicWorkRequest(movieId: Int): PeriodicWorkRequest {
        val inputData = Data.Builder()
            .putInt("MOVIE_ID", movieId)
            .build()
        val constraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
            .build()
        return PeriodicWorkRequest.Builder(MyWorkDetails::class.java,8, TimeUnit.HOURS)
            .setInputData(inputData)
            .setConstraints(constraint)
            .build()
    }
}