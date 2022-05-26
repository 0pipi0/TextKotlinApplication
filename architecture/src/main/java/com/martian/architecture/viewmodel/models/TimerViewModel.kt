package com.martian.architecture.viewmodel.models

import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.concurrent.timerTask

/**
 * Create by：Martian
 * on 2022/5/26
 */
class TimerViewModel : ViewModel() {

    private var timer: Timer? = null
    private var currentSeconds: Int = 0
    private var onTimeChangeListener:OnTimeChangeListener? = null
    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }

    fun startTimer(){
        timer?.cancel()
        currentSeconds = 0
        onTimeChangeListener?.onTimeChange(currentSeconds)
        timer  = Timer()
        var timerTask = timerTask {
            currentSeconds++
            onTimeChangeListener?.onTimeChange(currentSeconds)
        }
        timer?.schedule(timerTask,1000,1000)
    }

    @JvmName("setOnTimeChangeListener1")
    fun setOnTimeChangeListener(listener:OnTimeChangeListener){
        onTimeChangeListener = listener
    }
}

interface OnTimeChangeListener{
    fun onTimeChange(seconds:Int)
}