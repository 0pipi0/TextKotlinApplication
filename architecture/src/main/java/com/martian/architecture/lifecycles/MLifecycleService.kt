package com.martian.architecture.lifecycles

import android.content.Intent
import android.os.IBinder
import androidx.lifecycle.LifecycleService
import com.martian.architecture.lifecycles.function.PlayManager

class MLifecycleService : LifecycleService() {

    private lateinit var playManager: PlayManager

    override fun onCreate() {
        super.onCreate()
        playManager = PlayManager()
        lifecycle.addObserver(playManager)
    }

    override fun onBind(intent: Intent): IBinder {
        super.onBind(intent)
        TODO("Return the communication channel to the service.")
    }
}