package com.martian.code.coroutine

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Create by：Martian
 * on 2022/5/24
 */
class Base {
    suspend fun test() {
        coroutineScope {
            println("viewModelScope.launch ${Thread.currentThread()}")
            mainTest()
            println("viewModelScope.launch 结束了 ${Thread.currentThread()}")
        }
        test2()
    }
    // mainTest() 方法
    suspend fun mainTest() {
        println("mainTest() start start start " + Thread.currentThread())
        a()
        b()
        c()
        println("mainTest() end end end" + Thread.currentThread())
    }

    // 普通函数 test2()
    fun test2() {
        println("test2() doing doing doing " + Thread.currentThread())
    }
    //普通函数 a()
    fun a() {
        println("a() doing doing doing " + Thread.currentThread())
    }
    //普通函数 c()
    fun c() {
        println("c() doing doing doing " + Thread.currentThread())
    }
    // 挂起函数 b()
    suspend fun b() {
        println("b() start start start" + Thread.currentThread())
        coroutineScope {
            println("11111 线程 是" + Thread.currentThread())
            launch(IO) {
                println("22222 线程 是" + Thread.currentThread())
                delay(1000)
                println("22222 线程结束" + Thread.currentThread())
            }
            println("33333 线程 是" + Thread.currentThread())
        }
        println("b() end end end" + Thread.currentThread())
    }

}

suspend fun main() {
    Base().test()
}
