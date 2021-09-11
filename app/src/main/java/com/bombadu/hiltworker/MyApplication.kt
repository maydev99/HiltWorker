package com.bombadu.hiltworker

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.*
import com.bombadu.hiltworker.MyWorker.Companion.WORK_NAME
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class MyApplication: Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory
    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        applicationScope.launch {
            setupRecurringWork()
        }
    }

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    private fun setupRecurringWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val repeatRequest = PeriodicWorkRequestBuilder<Worker>(10, TimeUnit.SECONDS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatRequest
        )
    }

}