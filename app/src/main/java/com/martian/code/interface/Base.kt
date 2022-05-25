package com.martian.kotlin.`interface`

/**
 * Create by：Martian
 * on 2021/9/1
 */
fun main(args: Array<String>) {
    base()
}

fun base(){
    var child = Child()
    child.mathod()
    child.mathod2()
    println(child.type)
    child.type = 10
    println(child.type)
}