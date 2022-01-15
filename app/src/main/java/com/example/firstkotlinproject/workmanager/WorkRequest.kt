package com.example.firstkotlinproject.workmanager

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import java.util.concurrent.TimeUnit


class WorkRequest {

    private val constraint = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .setRequiresCharging(true)
        .build()

    val requestUpdater = PeriodicWorkRequest.Builder(MyWork::class.java,8,TimeUnit.HOURS)
        .setConstraints(constraint)
        .build()

}