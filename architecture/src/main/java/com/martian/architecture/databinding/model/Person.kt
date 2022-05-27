package com.martian.architecture.databinding.model

import androidx.databinding.*
import com.martian.architecture.BR

/**
 * Create by：Martian
 * on 2022/5/26
 */
class Person : BaseObservable() {
    var name: String = ""
        @Bindable
        get
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }
    var age: Int = 0
        @Bindable
        get
        set(value) {
            field = value
            notifyPropertyChanged(BR.age)
        }
    var showAge: Boolean = true
        @Bindable
        get
        set(value) {
            field = value
            notifyPropertyChanged(BR.showAge)
        }

//    var name = ObservableField(pName)
//    var age = ObservableInt(pAge)
//    var showAge = ObservableBoolean(pShow)
//    var name = pName
//    var age = pAge
//    var showAge = pShow

//    fun setName(mName:String){
//        name = mName
//    }
//
//    fun setAge(mAge:Int){
//        age.set(mAge)
//    }
//
//    fun setShowAge(mShowAge:Boolean){
//        showAge.set(mShowAge)
//    }

}