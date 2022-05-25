package com.martian.kotlin.expand

/**
 * Create by：Martian
 * on 2021/9/1
 * 伴生对象内的成员相当于 Java 中的静态成员，其生命周期伴随类始终，在伴生对象内部可以定义变量和函数，这些变量和函数可以直接用类名引用。
 * 对于伴生对象扩展函数，有两种形式，一种是在类内扩展，一种是在类外扩展，这两种形式扩展后的函数互不影响（甚至名称都可以相同），即使名称相同，它们也完全是两个不同的函数，并且有以下特点：
 * （1）类内扩展的伴随对象函数和类外扩展的伴随对象可以同名，它们是两个独立的函数，互不影响；
 * （2）当类内扩展的伴随对象函数和类外扩展的伴随对象同名时，类内的其它函数优先引用类内扩展的伴随对象函数，即对于类内其它成员函数来说，类内扩展屏蔽类外扩展；
 * （3）类内扩展的伴随对象函数只能被类内的函数引用，不能被类外的函数和伴随对象内的函数引用；
 * （4）类外扩展的伴随对象函数可以被伴随对象内的函数引用，；
 */
fun main(args: Array<String>) {
    testCompanion()
}

/**
 * expand method
 * 若扩展函数和成员函数一致，则使用该函数时，会优先使用成员函数。
 */
fun UserBean.myPrintln() {
    println("expand  name:$name")
}

fun C.foo() {
    println("C")
}

fun D.foo() {
    println("D")
}

fun myPrintln(c: C) {
    c.foo()
}

fun myPrintln2(d: D) {
    d.foo()
}

fun Any?.myToString(): String {
    if (this == null)
        return "null"
    return toString()
}

/**
 * expand
 */
fun testMethod() {
    var user = UserBean("martian");
    //若扩展函数和成员函数一致，则使用该函数时，会优先使用成员函数。
    user.myPrintln()


    //扩展函数是静态解析的，并不是接收者类型的虚拟成员，
    // 在调用扩展函数时，具体被调用的的是哪一个函数，
    // 由调用函数的的对象表达式来决定的，而不是动态的类型决定的:
    myPrintln(D())
    myPrintln(C())

    var bean = null
    println(bean.myToString())

}

/**
 * 扩展属性只能被声明为 val。
 */
 val <T> List<T>.lastIndex: Int
    get() = size - 1

/**
 * 扩展属性
 * 扩展属性允许定义在类或者kotlin文件中，不允许定义在函数中。
 * 初始化属性因为属性没有后端字段（backing field），
 * 所以不允许被初始化，只能由显式提供的 getter/setter 定义。
 */
fun testParams() {
    var list = listOf(1,2,3,4,5,6,7,8,9)
    println("list size is ${list.size}  last index is  ${list.lastIndex}")
}

fun UserBean.Companion.myPrintln(){
    println("userbean companion expand method println $name")
}

/**
 * companion
 */
fun testCompanion(){
    UserBean.companionPrintln()
    UserBean.myPrintln()

    //假如在调用某一个函数，而该函数在分发接受者和扩展接受者均存在，则以扩展接收者优先，
    // 要引用分发接收者的成员你可以使用限定的 this 语法。
    var e = E()
    var f = F()
    f.call(e)
}



