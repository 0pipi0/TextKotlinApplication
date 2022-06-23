package com.martian.architecture.workmanager.work

import android.app.Notification
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.work.*

/**
 * Create by：Martian
 * on 2022/6/21
 */
class CoroutineWorkerOne(@NonNull var context: Context, @NonNull workerParams: WorkerParameters) :CoroutineWorker(context, workerParams){

    var notificationId = 0
    val TAG: String = "CoroutineWorkerOne"

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getForegroundInfo(): ForegroundInfo {
        return ForegroundInfo(notificationId, createNotification())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotification() : Notification {
        return Notification.Builder(context).setChannelId("CoroutineWorkerOne")
            .setSmallIcon(com.martian.architecture.R.mipmap.ic_launcher)
            .build()
    }

    override suspend fun doWork(): Result {
        Log.i(TAG, Thread.currentThread().name)
        val result :String
        if(inputData.hasKeyWithValueOfType<String>("message")){
            result = "has input message :${inputData.getString("message")} and data :${inputData.getString("data")}"
        }else{
            result = "no input data"
        }
        return Result.success(Data.Builder().putString("result", result).build())
    }
}