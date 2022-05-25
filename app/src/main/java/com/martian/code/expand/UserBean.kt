package com.martian.kotlin.expand

/**
 * Create by：Martian
 * on 2021/9/1
 */
class UserBean(var name: String) {

    companion object {
        const val name: String = "companion martian"
        fun companionPrintln() {
            println("companion method $name")
        }
    }

    fun myPrintln() {
        println("UserBean  name:$name")
    }
}