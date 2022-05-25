package com.martian.kotlin.grammar


/**
 * Create by：Martian
 * on 2021/8/31
 */

fun main(args: Array<String>) {
//    textHelloWorld()
//    print(sum(1, 3, 6))
//    print(vars(1, 3, 6, 9, 11))
//    sumLambda()
//    testVarAndVal()
//    testString()
//    testNull()
//    println(getStringLength(123))
    testRange();
}

/**
 * 方法
 */
fun sum(a: Int, b: Int): Int {
    return a + b
}

/**
 * 方法简写
 */
fun sum(a: Int, b: Int, c: Int) = a + b + c

/**
 * 函数的变长参数可以用 vararg 关键字进行标识：
 */
fun vars(vararg v: Int): Int {
    var sum = 0;
    for (vt in v) {
        sum += vt
    }
    return sum
}

/**
 * lambda表达式使用实例：
 */
fun sumLambda() {
    val sumLambda: (Int, Int) -> Int = { x, y -> x + y }
    println(sumLambda(4, 5))
}

/**
 * 可变变量定义：var 关键字
 * 不可变变量定义：val 关键字，只能赋值一次的变量(类似Java中final修饰的变量)
 */
fun testVarAndVal(){
    var a:Int = 1
    var b = 1
    var c:Int
    c = 1

    val a2:Int = 2
    val b2 = 2
    val c2:Int
    c2 = 2
    println("a:${a}-b:${b}-c:${c}")
    println("a2:${a2}-b2:${b2}-c2:${c2}")
}

/**
 * $ 表示一个变量名或者变量值
 * $varName 表示变量值
 * ${varName.fun()} 表示变量的方法返回值:
 */
fun testString(){
    var string = "Hello World";
    val sum = sum(1,2,3)
    var vars = vars(10,11,12,13,14)
    println("string:$string--sum:$sum--vars:$vars")
    print("sumLambda:${sumLambda()}--testVarAndVal:${testVarAndVal()}")
}

/**
 * NULL检查机制
 * Kotlin的空安全设计对于声明可为空的参数，在使用时要进行空判断处理，有两种处理方式，
 * 字段后加!!像Java一样抛出空异常，
 * 另一种字段后加?可不做处理返回值为 null或配合?:做空判断处理
 */
fun testNull(){
    var age:String? = null
    var ageInt:Int = 0
    var ageInt2:Int = 0
    try {
        //age 没有做判空处理,会抛异常
        ageInt = age!!.toInt()
    } catch (e: Exception) {
        ageInt = -1
    }

    try {
        //!!会抛异常
        ageInt2 = age!!.toInt()
    } catch (e: Exception) {
        ageInt2 = -2
    }


    var age3:String? = "23"
    var ageInt3 = age3?.toInt()

    var age4:String? = null
    val ageInt4 = age4?.toInt()?:-4

    println("ageInt:$ageInt---ageInt2:$ageInt2---ageInt3:$ageInt3---ageInt4:$ageInt4")
}

/**
 * 类型检测及自动类型转换
 * 我们可以使用 is 运算符检测一个表达式是否某类型的一个实例(类似于Java中的instanceof关键字)。
 */
fun getStringLength(obj:Any):Int?{
    if(obj is String)
        return obj.length

    return null
}

/**
 * 区间
 * 区间表达式由具有操作符形式 .. 的 rangeTo 函数辅以 in 和 !in 形成。
 * 区间是为任何可比较类型定义的，但对于整型原生类型，它有一个优化的实现。以下是使用区间的一些示例:
 */
fun testRange(){
    for (a in 1..10)
        print("-a:$a")
    println("\n--------------")
    for(b in 10 downTo 1)
        print("-b:$b")
    println("\n--------------")
    for(c in 1 .. 10 step 2)
        print("-c:$c")
    println("\n--------------")
    for(d in 1 until 10)
        print("-d:$d")
}