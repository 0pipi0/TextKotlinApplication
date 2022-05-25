package com.martian.code.coroutine.cancel

import com.martian.code.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Create by：Martian
 * on 2022/5/24
 */
class Cancel {

    suspend fun cancel(){
        runBlocking {
            val job  = launch {
                log(1)
                try {
                    delay(1000)
                } catch (e: Exception) {
                    log("Exception:${e}")
                }
                log(2)
            }
            delay(100)
            log(3)
            job.cancel()
            log(4)
        }
    }

}

suspend fun main() {
    val cancel = Cancel();
    cancel.cancel()
}