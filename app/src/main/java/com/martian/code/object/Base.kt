package com.martian.kotlin.`object`

/**
 * Create by：Martian
 * on 2021/9/2
 */
fun main(args: Array<String>) {
    testObject3()
}

/**
 * 对象表达式
 */
fun testObject() {

    var a: A = object : A(1), B {
        override val y: Int = 15
        override fun foo() {
            println("foo")
        }
    }
    println("a y = ${a.y}")
//    a.foo()
    val person = object {
        var name :String = "martian"
        var age:Int = 18
    }
    println("person : ${person.name}  ${person.age}")


    val c = C()
    c.bar()


}

/**
 * 对象声明
 * Kotlin 使用 object 关键字来声明一个对象。
 * Kotlin 中我们可以方便的通过对象声明来获得一个单例。
 */
object DataProviderManager{
    var name:String = ""
    fun println(){
        println("DataProviderManager println name is $name")
    }
}

/**
 * 当然你也可以定义一个变量来获取这个对象，当时当你定义两个不同的变量来获取这个对象时，
 * 你会发现你并不能得到两个不同的变量。也就是说通过这种方式，我们获得一个单例。
 */
fun testObject2(){
    val manager1 = DataProviderManager;
    manager1.name = "martain"
    manager1.println()

    val manager2 = DataProviderManager;
//    manager2.name = "apple"
    manager2.println()
}
/**
 * 与对象表达式不同，当对象声明在另一个类的内部时，
 * 这个对象并不能通过外部类的实例访问到该对象，
 * 而只能通过类名来访问，同样该对象也不能直接访问到外部类的方法和变量。
 */
fun testObject3(){
    val c = C();
    // 错误，不能通过外部类的实例访问到该对象
//    c.User
    C.User.objectFun()
}
