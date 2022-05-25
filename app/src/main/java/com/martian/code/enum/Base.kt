package com.martian.kotlin.enum

/**
 * Create by：Martian
 * on 2021/9/1
 */
fun main(args: Array<String>) {

    for (terminal in Terminal.values()){
        println("${terminal.code}  ${terminal.msg}")
    }

    var pc = Terminal.PC;
    println("${pc.code}  ${pc.msg}")


    println("${Terminal.valueOf("UAV").code}")

}