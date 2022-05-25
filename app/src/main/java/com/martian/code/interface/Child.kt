package com.martian.kotlin.`interface`

/**
 * Create by：Martian
 * on 2021/9/1
 */
class Child:MyInterface {
    override var type: Int = 0
    override fun mathod() {
        println("Child mathod")
    }
    override fun mathod2(){
        println("Child mathod2")
    }
}