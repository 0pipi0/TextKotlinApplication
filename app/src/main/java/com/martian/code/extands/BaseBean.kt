package com.martian.kotlin.extands

/**
 * Create by：Martian
 * on 2021/9/1
 */
open class BaseBean(var name:String,var age:String){

    open var params:String = ""
    open fun myPrintln(){
        println("name:$name  age:$age")
    }
}