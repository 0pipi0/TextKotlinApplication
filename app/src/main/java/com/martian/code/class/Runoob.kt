package com.martian.kotlin.`class`

/**
 * Create by：Martian
 * on 2021/8/31
 */
class Runoob {

    var name:String = "Runoob"

    fun max(a: Int, b: Int): Int {
        return if (a > b) a else b
    }

    class Nested {
        fun min(a: Int, b: Int): Int {
            return if (a > b) b else a
        }
    }

    inner class Inner{
        var runoob = this@Runoob
        fun max(){
            println("inner class "+runoob.max(2,1))
        }
        var n = name
        fun name(){
            println("inner class name:$n")
        }
    }

    fun setInterFace(inter:InterFace){
        inter.myPrintln()
    }
}