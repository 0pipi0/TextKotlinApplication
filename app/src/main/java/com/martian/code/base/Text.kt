package com.martian.kotlin.base

class Text {

    /**
     * 换行
     */
    fun multiLine() {
        val text = """
                    |多行字符串
                    |菜鸟教程
                    |多行字符串
                    |Runoob
                    """.trimMargin()
        println(text)
    }

    /**
     * vararg 可变长参数函数
     */
    fun varargFunc(vararg v: Int) {
        for (vb in v) {
            print("$vb,")
        }
    }

    /**
     * lambda 表达式
     */
    fun lambdaFunc() {
        val max: (Int, Int) -> Int = { x, y -> x + y }
        print(max(5, 6))
    }

    /**
     *定义常量与变量
     */
    fun varFunc() {
        var a: Int
        val b = 2
        var c = 5
        c += 1
//        print(a)
        print(c)
    }

    /**
     * NULL检查机制
     */
    fun nullFunc() {

        //? 可为空
        var arg1: String? = "123"
        var arg11 = arg1?.toInt()
        println("arg11:$arg11")

        //!! 抛出空指针
        var arg2: String? = ""
        var arg22 = arg2!!.toInt()
        println("arg22:$arg22")

        //?: 做判空处理
        var arg3: String? = null
        var arg33 = arg3 ?: -1
        println("arg33:$arg33")
    }

    /**
     * 类型检测及自动类型转换
     */
    fun isFunc(obj: Any) {
        if (obj is String) {
            println("obj is String and length is ${obj.length}")
            return
        }
        if (obj is Number) {
            println("obj is Number and obj value $obj")
            return
        }
        println("obj not known")
    }

    /**
     * 区间
     */
    fun rangeToFunc() {
        for (i in 1..10) {
            print("$i,")
        }
        println()
        for (i in 10..1) {
            print("$i,")
        }
    }

    /**
     * 比较==，===
     * 三个等号 === 表示比较对象地址，两个 == 表示比较两个值大小
     */
    fun equalFun() {

        var a = 100;
        var b = a;
        var c = 100;
        println(a == b)
        println(a === b)
        println(a === c)

        var x: Int = 10000;
        var y: Int? = x;
        var z: Int? = 10000;
        println(x == y)
        println(x === y)
        println(x === z)


    }

    /**
     * 位操作符
     */
    fun bitFunc() {
        var a = 1;
        var l = a.shl(1)
        var r = a.shr(1)
        var ur = a.ushr(1)
        println("l:$l r:$r ur:$ur")

        var x = 1;
        var y = 2;
        println("and:${x.and(y)} or:${x.or(y)} xor:${x.xor(y)}")
    }

    /**
     * 字符
     */
    fun charFunc(a: Char) {
        // 错误：类型不兼容
//        if(a == 1){
//
//        }
    }

    /**
     * 数组
     */
    fun arrayFunc() {
        val array1 = arrayOf(1, 2, 3, 4)
        for (i in array1)
            print("$i,")
        println()
        println("array1- index:2 value:${array1[2]}")
        println()
        var array2 = Array(4, { i -> (i + 2) })
        for (i in array2)
            print("$i,")
        println()
        println("array2- index:2 value:${array2[2]}")
        println("array2- index:3 value:${array2.get(2)}")
    }

}

fun main(args: Array<String>) {
    val text = Text();
//    text.multiLine()
//    Text().varargFunc(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
//    text.lambdaFunc()
//    text.nullFunc()
//    text.isFunc("")
//    text.rangeToFunc()
//    text.equalFun()
//    text.bitFunc()
    text.arrayFunc()
}
