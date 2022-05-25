package com.martian.code.coroutine.launch

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Launch {

    /**
     * 模式	功能
     * DEFAULT	立即执行协程体
     * ATOMIC	立即执行协程体，但在开始运行之前无法取消
     * UNDISPATCHED	立即在当前线程执行协程体，直到第一个 suspend 调用
     * LAZY	只有在需要的情况下运行
     */

    /**
     * 默认的启动模式
     */
    suspend fun defalut(){
        println("start: ${Thread.currentThread().name}")
        var job = GlobalScope.launch {
            println("coroutines: ${Thread.currentThread().name}")
        }
        println("1111: ${Thread.currentThread().name}")
        job.join()
        println("end: ${Thread.currentThread().name}")
    }

    /**
     * 懒加载的模式
     */
    suspend fun lazy(){
        println("start: ${Thread.currentThread().name}")
        var job = GlobalScope.launch (start = CoroutineStart.LAZY){
            println("coroutines: ${Thread.currentThread().name}")
        }
        println("1111: ${Thread.currentThread().name}")
        job.join()
        println("end: ${Thread.currentThread().name}")
    }

    /**
     * atomic加载的模式
     */
    suspend fun atomic(){
        println("start: ${Thread.currentThread().name}")
        var job = GlobalScope.launch (start = CoroutineStart.ATOMIC){
            println("coroutines-111: ${Thread.currentThread().name}")
            delay(1000)
            println("coroutines-222: ${Thread.currentThread().name}")
        }
//        println("1111: ${Thread.currentThread().name}")
        job.cancel()
        println("end: ${Thread.currentThread().name}")
    }

    /**
     * undispatched加载的模式
     */
    suspend fun undispatched(){
        println("start: ${Thread.currentThread().name}")
        var job = GlobalScope.launch(start = CoroutineStart.UNDISPATCHED) {
            println("coroutines-111: ${Thread.currentThread().name}")
            delay(1000)
            println("coroutines-222: ${Thread.currentThread().name}")
        }
        println("1111: ${Thread.currentThread().name}")
        job.join()
        println("end: ${Thread.currentThread().name}")
    }
}

suspend fun main() {
 var launch = Launch();
//    launch.defalut()
//    launch.lazy()
    launch.undispatched()
}