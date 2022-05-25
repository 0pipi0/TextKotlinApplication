package com.martian.kotlin.extands

/**
 * Create by：Martian
 * on 2021/9/1
 */
fun main(args: Array<String>) {
    testParams()
}

/**
 * Constructor
 * override
 */
fun testConstructor(){
    val userBean = UserBean("martian","18","175")
    userBean.myPrintln()

    val pserson1 = PersonBean("pserson one","20")
    val pserson2 = PersonBean("pserson two","22","180")
    pserson1.myPrintln()
    pserson2.myPrintln()
}

/**
 * override
 * 在基类中，使用fun声明函数时，此函数默认为final修饰，不能被子类重写。如果允许子类重写该函数，那么就要手动添加 open 修饰它, 子类重写方法使用 override 关键词：
 * 如果有多个相同的方法（继承或者实现自其他类，如A、B类），则必须要重写该方法，使用super范型去选择性地调用父类的实现。
 */
fun testOverride(){
    val pserson1 = PersonBean("pserson one","20")
    val pserson2 = PersonBean("pserson two","22","180")
    pserson1.myPrintln()
    pserson2.myPrintln()
}

/**
 * 属性重写
 */
fun testParams(){
    val pserson1 = PersonBean("pserson one","20","180")
    pserson1.type = 10
    pserson1.myPrintln()

    val pserson2 = PersonBean("pserson two","22","180")
    pserson2.params = "params"
    pserson2.myPrintln()
}