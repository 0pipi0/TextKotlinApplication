package com.martian.kotlin.`for`

/**
 * Create by：Martian
 * on 2021/8/31
 */
fun main(args: Array<String>) {
    testReturn();
//    testBreak();
//    testBack()
//    testBack()
}

/**
 * return
 */
fun testReturn() {
    val items = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

    items.forEach() {
        if (it == 5) return
        println("it:$it")
    }
    //这个 return 表达式从最直接包围它的函数即 testReturn 中返回
    println("-------1-------")
    items.forEach(fun(value: Int) {
        if (value == 5) return
        println("it:$value")
    })
    println("-------2-------")
    items.forEach() lit@{
        if (it == 5) return@lit
        println("it:$it")
    }
    println("------end--------")

}


fun testBreak() {
    loop@ for (i in 1..10) {
        for (j in 1..10) {
            println("i:$i j:$j")
            if (i == 2 && j == 3) break@loop
        }
    }
}

/**
 * for
 */
fun testFor() {
    val items = listOf("apple", "orange", "banana")
    for (item in items)
        print(" $item ")
    println("\n--------------")

    for (index in items.indices)
        print("$index:${items.get(index)} ")
    println("\n--------------")

    for ((index, value) in items.withIndex())
        print("$index:${value} ")
}

/**
 * while
 */
fun testWhile() {
    var a = 5
    var b = a
    do {
        a--
        println("a:$a")
    } while (a > 0)
    println("\n--------------")

    while (b > 0) {
        b--
        println("b:$b")
    }
}

/**
 * back
 */
fun testBack() {

    for (a in 1..10) {
        if (a == 3) continue
        println(a)
        if (a == 8) break
    }
    println("--------------")
    loop@ for (a in 1..10) {
        for (b in 1..10) {
            if (b == 5) {
                break
            }

            if (a == 6) {
                break@loop
            }
            println("a:$a  b:$b")
        }
    }
    println("-back-")
    val items = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    items.forEach() lit@{
        if (it == 5) return@lit
        println("it:$it")
    }
    println("--------------")
    items.forEach() {
        if (it == 5) return
        println("it:$it")
    }
    println("------end--------")
}

