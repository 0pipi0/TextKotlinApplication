package com.martian.code.coroutine.channel

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Create by：Martian
 * on 2022/5/24
 */
class MChannel {

    suspend fun test(){
        var channels = List(10){
            println("$it")
            Channel<Int>()
        }
//        channels.forEach {
//            println("$it")
//        }
        println("size: ${channels.size}")

        channels.forEachIndexed(fun (index,channel){
            println("index: ${index}")
            delay(100)
            channel.send(index)

        })
        channels.forEach { channel ->
            GlobalScope.launch {
                var data =    channel.receive();
                println("$data")
//                    channel.onReceive {
//                        it
//                    }
            }
            // OR
//                channel.onReceiveOrNull { it }
        }
//        var result = select<Int?> {
//
//        }


//        println("$result")


    }
}

suspend fun main() {
    var channel = MChannel()
    channel.test()
}