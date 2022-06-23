package com.martian.architecture.workmanager.work

import android.content.Context
import android.util.Log
import androidx.annotation.NonNull
import androidx.work.*
import kotlinx.coroutines.delay

/**
 * Create by：Martian
 * on 2022/6/21
 */
class ProgressWorker(@NonNull var context: Context, @NonNull workerParams: WorkerParameters) :CoroutineWorker(context, workerParams){

    val TAG: String = "ProgressWorker"

    companion object {
        const val Progress = "Progress"
        private const val delayDuration = 1000L
    }

    override suspend fun doWork(): Result {
        Log.i(TAG, Thread.currentThread().name)
        setProgress(workDataOf(Progress to 0))
        delay(delayDuration)
        setProgress(workDataOf(Progress to 50))
        delay(delayDuration)
        setProgress(workDataOf(Progress to 90))
        delay(delayDuration)
        setProgress(workDataOf(Progress to 100))
        delay(delayDuration)
        return Result.success()
    }
}