package com.bombadu.hiltworker

import android.content.Context
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.work.Worker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import javax.inject.Scope

class MyWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
) : Worker(appContext, workerParams)  {

    override fun doWork(): Result {

        return try {
            val repository = DefaultMyRepository()
            repository.printToLog()
            Log.i("TAG", "End: ${Util.getTime()}")
            Result.success()


        } catch (e: Exception) {
            Result.retry()
        }

    }

    companion object {
        const val WORK_NAME = "RefreshData"
    }
}

