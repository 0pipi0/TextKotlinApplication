package com.martian.architecture

import android.app.Application
import androidx.work.Configuration
import java.util.concurrent.Executors

/**
 * Create by：Martian
 * on 2022/6/23
 */
class MyApplication :Application(), Configuration.Provider{
    override fun getWorkManagerConfiguration(): Configuration {

        return Configuration.Builder()
            .setDefaultProcessName(this.packageName)
            .setExecutor(Executors.newFixedThreadPool(8))
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .build()
    }
}