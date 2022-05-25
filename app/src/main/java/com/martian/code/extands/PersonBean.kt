package com.martian.kotlin.extands

/**
 * Create by：Martian
 * on 2021/9/1
 */
class PersonBean:BaseBean,InterFace{
    var height:String? = null
    override var type: Int = 0
        get() = field
        set(value) {
            field = value
        }
    override var params: String = ""
        get() = field
        set(value) {
            field = value
        }
    constructor(name: String,age:String):super(name,age){}
    constructor(name: String,age:String, height:String):super(name,age){
        this.height = height
    }

    override fun myPrintln() {
        super<InterFace>.myPrintln()
        if(height.isNullOrEmpty()){
            super<BaseBean>.myPrintln()
        }else{
            println("name:$name  age:$age  height:$height  type:$type  params:$params")
        }
    }
}