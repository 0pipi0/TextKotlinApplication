package com.martian.architecture.workmanager.work

import android.content.Context
import android.util.Log
import androidx.annotation.NonNull
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * Create by：Martian
 * on 2022/6/21
 */
class WorkerThree(@NonNull context: Context, @NonNull workerParams: WorkerParameters) : Worker(context, workerParams) {
    val TAG: String = "WorkerThree"
    override fun doWork(): Result {
        Log.i(TAG, Thread.currentThread().name)
        return Result.success(Data.Builder().putString("message", "martian").build())
    }
}