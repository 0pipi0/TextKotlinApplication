package com.martian.kotlin.expand

/**
 * Create by：Martian
 * on 2021/9/1
 */
class F{
    fun bar(){
        println("F bar")
    }

    fun E.foo(){
        bar()
        this@F.bar()
    }

    fun call(e:E){
        e.foo()
    }
}
