package com.martian.kotlin.datatype

/**
 * Create by：Martian
 * on 2021/8/31
 */
fun main(args: Array<String>) {
//    testBase()
//    testCompose()
//    testMove()
//    testArray()
    testString()
}

/**
 * 字面常量
 * 十进制：123
 * 长整型以大写的 L 结尾：123L
 * 16 进制以 0x 开头：0x0F
 * 2 进制以 0b 开头：0b00001011
 * 注意：8进制不支持
 *
 */
fun testBase() {
    var a = 100_000_000
    var b = 123_456_789L
    var c = 0x0f0f_0000
    var d = 0b1010_1010
    println("a:$a-b:$b-c:$c-d:$d")
}

/**
 * 比较两个数字
 * Kotlin 中没有基础数据类型，只有封装的数字类型，你每定义的一个变量，其实 Kotlin 帮你封装了一个对象，这样可以保证不会出现空指针。
 * 数字类型也一样，所以在比较两个数字的时候，就有比较数据大小和比较两个对象是否相同的区别了。
 * 在 Kotlin 中，三个等号 === 表示比较对象地址，两个 == 表示比较两个值大小。
 */
fun testCompose() {
    val a = 1000
    val b: Int = 1000
    println("a===b：${a === b}---a==b：${a == b}")

    val c = 1000
    val d: Int = c
    println("c===d：${c === d}---c==d：${c == d}")


    val e: Int? = a
    val f: Int? = a
    println("e===f：${e === f}---e==f：${e == f}")
}

/**
 * shl(bits) – 左移位 (Java’s <<)
 * shr(bits) – 右移位 (Java’s >>)
 * ushr(bits) – 无符号右移位 (Java’s >>>)
 * and(bits) – 与
 * or(bits) – 或
 * xor(bits) – 异或
 * inv() – 反向
 */
fun testMove() {
    val a = 1
    val b = -1
    println("shl：${a.shl(2)}--shl：${b.shl(2)}")
    println("shr：${a.shr(2)}--shr：${b.shr(2)}")
    println("shr：${a.ushr(2)}--ushr：${b.ushr(2)}")
}

/**
 * 数组
 * 数组用类 Array 实现，并且还有一个 size 属性及 get 和 set 方法，由于使用 [] 重载了 get 和 set 方法，所以我们可以通过下标很方便的获取或者设置数组对应位置的值。
 * 数组的创建两种方式：一种是使用函数arrayOf()；另外一种是使用工厂函数。如下所示，我们分别是两种方式创建了两个数组：
 */
fun testArray() {
    var array1 = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    var array2 = Array(9, { i -> (i + 2) })
    var array3 = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    for (a in array1)
        print("-a:$a")
    println("\n--------------")
    for (b in array2)
        print("-b:$b")
    println("\n--------------")
    for (c in array3)
        print("-c:$c")
}

/**
 *字符串
 */
fun testString(){
    val str = "Hello World"
    val str1 = """
        |line one
        |line two
        |line three
        |""".trimMargin()
    val str2 = "${'$'}9.99"
    for (a in str)
        print("$a ")
    println("\n--------------")
    for (a in str1)
        print("$a ")
    println("\n--------------")
    print(str2)
}


