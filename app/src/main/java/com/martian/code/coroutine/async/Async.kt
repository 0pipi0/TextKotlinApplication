package com.martian.code.coroutine.async

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlin.system.measureTimeMillis

/**
 * Create by：Martian
 * on 2022/5/24
 */
class Async {

    // todo
    suspend fun testAsync() {
        coroutineScope {
            val time = measureTimeMillis {
                val one = async { doSomethingsOne() }
                val two = async { doSomethingsTwo() }
                println("the result is ${one.await() + two.await()}")
            }
            println("完成时间为 time is $time ms")
        }
    }

    private suspend fun doSomethingsOne(): Int {
        // 假设做了些事情，耗时
        delay(1000L)
        return 17
    }

    private suspend fun doSomethingsTwo(): Int {
        // 假设做了些事情，耗时
        delay(2000L)
        return 30
    }
}

suspend fun main() {
    var async = Async()
    async.testAsync()
}