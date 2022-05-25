package com.martian.kotlin.conditioncontrol

/**
 * Create by：Martian
 * on 2021/8/31
 */
fun main(args: Array<String>) {
//    testIf()
    testWhen("apple")
}

/**
 * if
 */
fun testIf() {
    val a = 1
    val b = 2
    var max: Int = b
    if (a > b) max = a
    println("max:$max")

    val max1 = if (a > b) a else b
    println("max1:$max1")

    if (a in 0..5)
        println("a in 0..5")
}

/**
 * when
 * when 将它的参数和所有的分支条件顺序比较，直到某个分支满足条件。
 * when 既可以被当做表达式使用也可以被当做语句使用。如果它被当做表达式，符合条件的分支的值就是整个表达式的值，如果当做语句使用， 则忽略个别分支的值。
 * when 类似其他语言的 switch 操作符。其最简单的形式如下：
 */
fun testWhen(a: Any) {
    when (a) {
        1, 2 -> print("1,2")
        in 3..10 -> print("in 3..10")
        !in 11..20 -> print("!in 11..20")
        "string" -> print("string")
        true, false -> print("boolean")
        else -> print("else")
    }
    println("\n--------------")
    val items = setOf("apple","orange","banana")
    when{
      a in items -> print("$a in items")
        else -> print("not in item")
    }
}