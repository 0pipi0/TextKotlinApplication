package com.martian.kotlin.by

/**
 * Create by：Martian
 * on 2021/9/2
 */
fun main(args: Array<String>) {
    testBy()
}

fun testBy(){
    var a = A(10)
    var b = B(a)
    b.print()
}
