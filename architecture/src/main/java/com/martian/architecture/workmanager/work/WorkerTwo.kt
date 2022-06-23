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
class WorkerTwo(@NonNull context: Context, @NonNull workerParams: WorkerParameters) : Worker(context, workerParams) {
    val TAG: String = "WorkerTwo"
    override fun doWork(): Result {
        Log.i(TAG, Thread.currentThread().name)
        val result :String
        if(!inputData.keyValueMap.keys.contains("message")){
            result = "no input data"
            return Result.retry()
        }
        var message = ""
        var data = ""
        inputData.keyValueMap.entries.forEach {
            when(it.key){
                "message" ->{ message = getValue(it.value) }
                "data" ->{ data = getValue(it.value) }
                else ->{}
            }
        }
        result = "has input message :${message} and data :${data}"
        return Result.success(Data.Builder().putString("result", result).build())
    }

    fun getValue(any: Any):String{
        if(any is String){
            return any as String
        }else if(any is Array<*>){
            var stringBuffer = StringBuffer()
            (any as Array<*>).forEach {
                stringBuffer.append(it.toString())
            }
            return  stringBuffer.toString()
        }
        return ""
    }
}