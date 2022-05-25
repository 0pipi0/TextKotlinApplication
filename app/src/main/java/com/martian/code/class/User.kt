package com.martian.kotlin.`class`

/**
 * Create by：Martian
 * on 2021/8/31
 */
class User(name: String):BaseBean() {
    var name: String = "martian"
        get() = field
        set(value) {
            field = value
        }
    var age: String = "18"
        get() = field
        set(value) {
            field = value
        }
    var height: Float = 173.0f
        private set

    init {
        this.name = name
    }

    constructor(name: String, age: String) : this(name) {
        this.age = age
    }

    override fun myPrintln() {
        println("name:$name  age:$age  height:$height")
    }

}