package com.martian.kotlin.inline

class InlineClass {
//    inline fun doInline() {
//        print("inline")
//    }
//
    fun doNoInline() {
        print("noInline")
    }

    inline fun advancedFunction(func: () -> Unit) {
        print("advanced")
        func()
    }

    fun test() {
//        doInline()
//
        doNoInline()

        advancedFunction { print("func") }
    }
}
