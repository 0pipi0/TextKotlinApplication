package com.martian.code.coroutine.dispatch

import kotlinx.coroutines.*
import java.util.concurrent.Executors
import kotlin.coroutines.coroutineContext

class Dispatch {

    /**
                        Jvm	        Js	             Native
        Default	      线程池	主线程循环	       主线程循环
        Main	    UI 线程	   与 Default 相同	与 Default 相同
        Unconfined	直接执行	    直接执行	           直接执行
        IO	         线程池	      --	             --
     */

    /**
     * 默认的启动模式
     */
    suspend fun defalut(){
        var i = 0
        Executors.newFixedThreadPool(10)
            .asCoroutineDispatcher().use { dispatcher ->
                List(100) {
                    GlobalScope.launch(dispatcher) {
//                        println("$i: ${Thread.currentThread().name}")
                        i++
                    }
                }.forEach {
                    it.join()
                }
            }
        println("end---$i: ${Thread.currentThread().name}")

//        var j = 0;
//        for (i in 1 .. 100){
//            Thread(){
//                j++
//            }.start()
//        }
//        println("end-j:$j: ${Thread.currentThread().name}")

    }

    /**
     * coroutineContext
     */
    suspend fun main(){
        GlobalScope.launch {
            println(coroutineContext[Job]) // "coroutine#1":StandaloneCoroutine{Active}@1ff62014
        }
        println(coroutineContext[Job]) // null，suspend main 虽然也是协程体，但它是更底层的逻辑，因此没有 Job 实例
    }


    suspend inline fun Job.Key.currentJob() = coroutineContext[Job]
    suspend fun coroutineJob(){
        GlobalScope.launch {
            println(Job.currentJob())
        }
        println(Job.currentJob())
    }


    /**
     * 为协程添加名称
     */
    suspend fun coroutineName(){
        println("start: ${Thread.currentThread().name}")
        var job = GlobalScope.launch (CoroutineName("martian"),start = CoroutineStart.ATOMIC){
//            println("currentJob: ${this.}")
            println("name: ${this.coroutineContext[CoroutineName]}")
            println("coroutines-111: ${Thread.currentThread().name}")
            delay(1000)
            println("coroutines-222: ${Thread.currentThread().name}")
        }
        println("1111: ${job}")
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
 var dispatch = Dispatch();
//     dispatch.defalut()
//     dispatch.main()
//     dispatch.coroutineJob()
     dispatch.coroutineName()
//    launch.undispatched()
}