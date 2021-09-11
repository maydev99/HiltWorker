package com.bombadu.hiltworker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject


class MyWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: MyRepository

) : Worker(appContext, workerParams)  {
    override fun doWork(): Result {

        return try {
            repository.printToLog()
            Result.success()

        } catch (e: Exception) {
            Result.retry()
        }
    }

    companion object {
        const val WORK_NAME = "RefreshData"
    }
}

