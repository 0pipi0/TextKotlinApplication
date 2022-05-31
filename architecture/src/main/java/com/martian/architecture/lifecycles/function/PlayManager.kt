package com.martian.architecture.lifecycles.function

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * Create by：Martian
 * on 2022/5/31
 */
class PlayManager :LifecycleObserver{

    /**
     * createPlay
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun createPlay(){
        println("PlayManager:createPlay")
    }

    /**
     * startPlay
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startPlay(){
        println("PlayManager:startPlay")
    }
    /**
     * stopPlay
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stopPlay(){
        println("PlayManager:stopPlay")
    }
    /**
     * destoryPlay
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destoryPlay(){
        println("PlayManager:destoryPlay")
    }
}