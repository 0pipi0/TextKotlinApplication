package com.martian.architecture.livedata.viewmodel

import android.view.View
import androidx.lifecycle.*
import kotlinx.coroutines.delay
import java.util.*
import java.util.Observer

/**
 * Create by：Martian
 * on 2022/5/30
 */
class MViewModel : ViewModel(){

    var count:MutableLiveData<Int> = MutableLiveData<Int>()
    var currentTime:LiveData<Long> = getCurrentTime()
    var currentTimeFormate = currentTime.map {
        timeStampToTime(it)
    }
    override fun onCleared() {
        super.onCleared()
    }

    fun addCount(view: View){
        count.value = (count.value?:0)+1
    }

    fun reduce(view: View){
        count.value = (count.value?:0)-1
    }

     @JvmName("getCurrentTime1")
     fun getCurrentTime():LiveData<Long> =
        liveData {
            while (true) {
                emit(System.currentTimeMillis())
                delay(1000)
            }
        }

    private  fun timeStampToTime(timestamp: Long): String {
        val date = Date(timestamp)
        return date.toString()
    }

    var liveData1 : MutableLiveData<String> = MutableLiveData<String>()
    var liveData2 : MutableLiveData<String> = MutableLiveData<String>()
    var switchLiveData : MutableLiveData<Boolean> = MutableLiveData(false)

    fun switchLiveDataF(view: View){
        switchLiveData.value = !switchLiveData.value!!
    }

    fun liveData1F(view: View){
        liveData1.value = "111111111111111"
    }
    fun liveData2F(view: View){
        liveData2.value = "222222222222222"
    }

    var liveData3 : MutableLiveData<String> = MutableLiveData<String>()
    var liveData4 : MutableLiveData<String> = MutableLiveData<String>()
    var mediatorLiveData : MediatorLiveData<String> = MediatorLiveData<String>()

    fun mediatorLiveDataF(view: View){
        liveData3.value = "333333333333333"
        liveData4.value = "444444444444444"
    }

    fun liveData3F(view: View){
        liveData3.value = "333333333333333"
    }
    fun liveData4F(view: View){
        liveData4.value = "444444444444444"
    }
}