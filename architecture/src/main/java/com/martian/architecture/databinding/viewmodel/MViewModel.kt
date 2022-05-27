package com.martian.architecture.databinding.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.martian.architecture.databinding.model.Person

/**
 * Create by：Martian
 * on 2022/5/26
 */
class MViewModel : ViewModel() {

     var person = ObservableField<Person>()
//    var name = ObservableField<String>()
//    var age = ObservableInt()
//    var showAge = ObservableBoolean()

    override fun onCleared() {
        super.onCleared()
    }

    fun initPerson() {
        val mPserson = Person()
        mPserson.name = "martian"
        mPserson.age = 18
        mPserson.showAge = true
        person.set(mPserson)
    }




}