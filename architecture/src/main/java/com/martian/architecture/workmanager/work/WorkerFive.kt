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
class WorkerFive(@NonNull context: Context, @NonNull workerParams: WorkerParameters) : Worker(context, workerParams) {
    val TAG: String = "WorkerFive"
    override fun doWork(): Result {
        Log.i(TAG, Thread.currentThread().name)
        if(!inputData.keyValueMap.keys.contains("message")){
            return Result.failure()
        }
        var message = ""
        inputData.keyValueMap.entries.forEach {
            when(it.key){
                "message" ->{ message = getValue(it.value) }
                else ->{}
            }
        }
        return Result.success(Data.Builder().putString("result", message).build())
    }

    fun getValue(any: Any):String{
        if(any is String){
            return any as String
        }else if(any is Array<*>){
            var stringBuffer = StringBuffer()
            (any as Array<*>).forEach {
                stringBuffer.append(it.toString()).append(",")
            }
            return  stringBuffer.toString()
        }
        return ""
    }
}