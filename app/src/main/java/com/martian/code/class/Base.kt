package com.martian.kotlin.`class`

/**
 * Create by：Martian
 * on 2021/8/31
 *
 * 类的修饰符
 * 类的修饰符包括 classModifier 和_accessModifier_:
 *
 * classModifier: 类属性修饰符，标示类本身特性。
 * abstract    // 抽象类
 * final       // 类不可继承，默认属性
 * enum        // 枚举类
 * open        // 类可继承，类默认是final的
 * annotation  // 注解类
 *
 * accessModifier: 访问权限修饰符
 * private    // 仅在同一个文件中可见
 * protected  // 同一个文件中或子类可见
 * public     // 所有调用的地方都可见
 * internal   // 同一个模块中可见
 *
 */
fun main(args: Array<String>) {
    testInnerClass()
}

/**
 * base
 */
fun base() {
    var runoob = Runoob()
    println(runoob.max(2, 3))
}

/**
 * get set
 */
fun testGetAndSet() {
    val user = User("martian3")
    println(user.name + ":" + user.age)
    user.name = "martian5"
    user.age = "20"
    println(user.name + ":" + user.age)

    val user2 = User("apple", "20")
    println(user2.name + ":" + user2.age)

}

/**
 * abstract
 */
fun testAbstract() {
    val user = User("abstract", "21");
    user.myPrintln()
}

/**
 * 嵌套类
 * 内部类
 * inner class
 */
fun testInnerClass() {
    //外部类
    val outer = Runoob()
    println(outer.max(2, 1))

    //嵌套类
    var nested = Runoob.Nested()
    println(nested.min(2, 1))

    //内部类
    var inner = Runoob().Inner()
    inner.max()
    inner.name()

    //匿名内部类
    var interFace = Runoob()
    interFace.setInterFace(object : InterFace {
        override fun myPrintln() {
            println("interface println")
        }
    })

}