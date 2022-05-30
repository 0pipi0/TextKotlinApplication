package com.martian.architecture.livedata.util

import androidx.lifecycle.LiveData

/**
 * Create by：Martian
 * on 2022/5/30
 */
class MyLiveData : LiveData<Int>() {

    override fun onActive() {
        super.onActive()
    }

    override fun onInactive() {
        super.onInactive()
    }
}