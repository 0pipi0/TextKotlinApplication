package com.martian.code.coroutine.exception

import com.martian.code.log
import kotlinx.coroutines.*

/**
 * Create by：Martian
 * on 2022/5/24
 */
class MException {

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("${coroutineContext[CoroutineName]} $throwable")
    }

    suspend fun main() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("Throws an exception with message: ${throwable.message}")
        }

        println(1)
        GlobalScope.launch(exceptionHandler) {
            throw ArithmeticException("Hey!")
        }.join()
        println(2)
    }

    /**
     * GlobeScope、coroutineScope、supervisorScope
    11:37:36:208 [main] 1
    11:37:36:255 [main] 2
    11:37:36:325 [DefaultDispatcher-worker-1] 3
    11:37:36:325 [DefaultDispatcher-worker-1] 5
    11:37:36:326 [DefaultDispatcher-worker-3] 4
    11:37:36:331 [main] 6
    11:37:36:336 [DefaultDispatcher-worker-1] 7
    11:37:36:336 [main] 8
    11:37:36:441 [DefaultDispatcher-worker-1] 10. kotlinx.coroutines.JobCancellationException: ScopeCoroutine is cancelling; job=ScopeCoroutine{Cancelling}@2bc92d2f
    11:37:36:445 [DefaultDispatcher-worker-1] 12. java.lang.ArithmeticException: Hey!!
    11:37:36:445 [DefaultDispatcher-worker-1] 13
     */
    suspend fun coroutineScope(){
        println(1)
        try {
            coroutineScope { //①
                println(2)
                launch { // ②
                    println(3)
                    launch { // ③
                        println(4)
                        delay(100)
                        throw ArithmeticException("Hey!!")
                    }
                    println(5)
                }
                println(6)
                val job = launch { // ④
                    println(7)
                    delay(1000)
                }
                try {
                    println(8)
                    job.join()
                    println("9")
                } catch (e: Exception) {
                    println("10. $e")
                }
            }
            println(11)
        } catch (e: Exception) {
            println("12. $e")
        }
        println(13)
    }
    /**
     * GlobeScope、coroutineScope、supervisorScope

     */
    suspend fun supervisorScope(){
        log(1)
        try {
            supervisorScope { //①
                log(2)
                launch(exceptionHandler+CoroutineName("②")) { // ②
                    log(3)
                    launch(exceptionHandler+CoroutineName("③"))  { // ③
                        log(4)
                        delay(100)
                        throw ArithmeticException("Hey!!")
                    }
                    log(5)
                }
                log(6)
                val job = launch(exceptionHandler+CoroutineName("④"))  { // ④
                    log(7)
                    delay(1000)
                }
                try {
                    log(8)
                    job.join()
                    log("9")
                } catch (e: Exception) {
                    log("10. $e")
                }
            }
            log(11)
        } catch (e: Exception) {
            log("12. $e")
        }
        log(13)
    }

    /**
     *  await
     */
    suspend fun await(){
        var deferred = GlobalScope.async {
            throw ArithmeticException()
            log("3")
        }
        try {
            val value = deferred.await();
//            log("1. $value")
        } catch (e: Exception) {
            log("2. $e")
        }
    }

    /**
     *  join
     */
    suspend fun join(){
        var deferred = GlobalScope.async {
            throw ArithmeticException()
            log("3")
        }
//        try {
            deferred.join();
            log("1")
//        } catch (e: Exception) {
//            log("2. $e")
//        }
    }
}

suspend fun main() {
//    MException().main();
//    MException().coroutineScope();
//    MException().supervisorScope();
//    MException().await();
    MException().join();
}