package com.martian.architecture.workmanager.work

import android.content.Context
import androidx.work.Data
import androidx.work.WorkerParameters
import androidx.work.multiprocess.RemoteCoroutineWorker

/**
 * Create by：Martian
 * on 2022/6/23
 */
class RemoteCoroutineWorkerOne(context: Context, parameters: WorkerParameters) : RemoteCoroutineWorker(context, parameters){
    private val TAG: String = "RemoteCoroutineWorker"
    override suspend fun doRemoteWork(): Result {
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