package com.martian.code.coroutine.flow

import com.martian.code.log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * Create by：Martian
 * on 2022/5/25
 */
class MFlow {
    suspend fun test() {
        val flow = flow<Int> {
            (1..3).forEach {
                emit(it)
                log("emit $it")
//                delay(1000)
                throw ArithmeticException("error")
            }
        }.catch { it: Throwable ->
            println("caught error: $it")
        }.onCompletion {
            println("onCompletion")
        }

        GlobalScope.launch {
//            flow.flowOn(Dispatchers.IO).collect {
//                log("collect $it")
//            }
            flow.collect {
                log("collect1 $it")
            }
            flow.collect {
                log("collect2 $it")
            }
        }.join()
    }

    suspend fun test2() {
        val flow = flow<Int> {
            (1..3).forEach {
                emit(it)
                log("emit $it")
                delay(1000)
//                throw ArithmeticException("error")
            }
        }.catch { it: Throwable ->
            println("caught error: $it")
        }.onCompletion {
            println("onCompletion")
        }
        flow.collect {
            log("collect $it")
        }
    }

    /**
     * flow 消费
     */
    suspend fun test3() {
        val flow = flow<Int> {
            (1..3).forEach {
                emit(it)
                log("emit $it")
                delay(1000)
//                throw ArithmeticException("error")
            }
        }.catch { it: Throwable ->
            println("caught error: $it")
        }.onCompletion {
            println("onCompletion")
        }

        GlobalScope.launch {
            flow.collect {
                println("collect: $it")
            }
        }.join()
    }

    /**
     * flow 消费
     */
    suspend fun test4() {
        val flow = flow<Int> {
            (1..3).forEach {
                emit(it)
                log("emit $it")
                delay(1000)
//                throw ArithmeticException("error")
            }
        }.catch { it: Throwable ->
            println("caught error: $it")
        }.onEach {
            log("onEach $it")
        }.onCompletion {
            println("onCompletion")
        }

//        GlobalScope.launch{
//            flow.collect()
//        }.join()

        flow.launchIn(GlobalScope).join()
    }


    /**
     * flow 取消
     */
    suspend fun cancel() {
        val job = GlobalScope.launch {
            val flow = flow<Int> {
                (1..3).forEach {
                    delay(1000)
                    emit(it)
                    log("emit $it")
//                throw ArithmeticException("error")
                }
            }.catch { it: Throwable ->
                println("caught error: $it")
            }.onEach {
                log("onEach $it")
            }.onCompletion {
                println("onCompletion")
            }
            flow.collect()
        }
        delay(2500)
        job.cancelAndJoin()


//        flow.launchIn(GlobalScope)
    }

    /**
     * flow 取消
     */
    suspend fun cancel2() {
        val flow = flow<Int> {
            (1..3).forEach {
                delay(1000)
                emit(it)
                log("emit $it")
//                throw ArithmeticException("error")
            }
        }.catch { it: Throwable ->
            println("caught error: $it")
        }.onEach {
            log("onEach $it")
        }.onCompletion {
            println("onCompletion")
        }
        val job = GlobalScope.launch {
            flow.collect()
        }
        delay(2500)
        job.cancelAndJoin()

//        flow.launchIn(GlobalScope)
    }

    /**
     * flow 切换调度器的方式创建
     */
    suspend fun createByDispatchers() {
        val channelFlow = channelFlow<Int> {
            send(1)
            withContext(Dispatchers.IO) {
                send(2)
            }
            send(3)

        }.catch { it: Throwable ->
            println("caught error: $it")
        }.onEach {
            log("onEach $it")
        }.onCompletion {
            println("onCompletion")
        }

        val job = GlobalScope.launch {
            channelFlow.collect()
        }.join()
//        delay(2500)
//        job.cancelAndJoin()

//        flow.launchIn(GlobalScope)
    }

    /**
     * flow 背压
     */
    suspend fun backPressure() {
//        val flow = flow<Int> {
//            List(1000){
//                emit(it)
//                log("emit $it")
//            }
//        }.catch { it:Throwable->
//            println("caught error: $it")
//        }.onCompletion {
//            println("onCompletion")
//        }
//
//        GlobalScope.launch{
//            flow.collect{
//                delay(1000)
//                log("collect $it")
//            }
//        }.join()

        flow {
            List(10000000) {
                emit(it)
                println("emit $it")
            }
        }
//            .conflate()
//            .collect{ value ->
//                println("Collecting $value")
//                delay(1)
//                println("$value collected")
//
//            }
            .collectLatest { value ->
                println("Collecting $value")
                delay(10)
                println("$value collected")
            }
    }


    /**
     * 变换 转换
     */
    suspend fun transform() {
        flow {
            List(10) {
                emit(it)
            }
        }.map {
            it * 2
        }.onEach {
            log("$it")
        }.onCompletion {
            println("onCompletion")
        }.launchIn(GlobalScope).join()
    }

    /**
     * 变换 嵌套
     */
    suspend fun transform2() {
        flow {
            List(10) {
                emit(it)
            }
        }.map {
            flow {
                List(it) {
                    emit(it * 2)
                }
            }
        }.collect {
            println("flow---- $it")
            it.collect {
                log("$it")
            }
        }
    }

    /**
     * 变换 flattenConcat、flattenMerge
     */
    suspend fun transform3() {
        flow {
            List(10) {
                emit(it)
            }
        }.map {
            flow {
                List(it) {
                    emit(it * 2)
                }
            }
        }.flattenConcat()
            .collect {
//                println("flow---- $it")
                log("$it")

            }
    }
}

suspend fun main() {
//    MFlow().test()
//    MFlow().test2()
//    MFlow().test3()
//    MFlow().test4()
//    MFlow().cancel()
//    MFlow().cancel2()
//    MFlow().createByDispatchers()
//    MFlow().backPressure()
//    MFlow().transform()
//    MFlow().transform2()
    MFlow().transform3()
}