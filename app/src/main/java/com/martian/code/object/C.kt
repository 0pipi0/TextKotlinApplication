package com.martian.kotlin.`object`

/**
 * Create by：Martian
 * on 2021/9/2
 */
class C {

    // 私有函数，所以其返回类型是匿名对象类型
    private fun foo() = object {
        val x: String = "martian"
    }

    // 公有函数，所以其返回类型是 Any
    fun publiceFoo() = object {
        val x: String = "martian"
    }

    fun bar() {
        val x1 = foo().x // 没问题
        println("private foo x : $x1")

//        val x2 = publiceFoo().x  错误：未能解析的引用“x”
    }

    object User {
        var name: String = "martian"
        var age: Int = 18
        fun objectFun() {
            //错误，不能访问到外部类的方法和变量
            //bar()
            println("$name  $age")
        }
    }
}