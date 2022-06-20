package com.martian.architecture.room.veiwmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Transaction
import com.martian.architecture.room.dao.CatDao
import com.martian.architecture.room.dao.DogDao
import com.martian.architecture.room.dao.PersonDao
import com.martian.architecture.room.data.Cat
import com.martian.architecture.room.data.Dog
import com.martian.architecture.room.data.Person
import com.martian.architecture.room.database.AppDataBase

/**
 * Create by：Martian
 * on 2022/6/9
 */
class RoomViewModel : ViewModel() {

    private var personDao: PersonDao? = null
    private var dogDao: DogDao? = null
    private var catDao: CatDao? = null
    private lateinit var context: Context

    fun initDataBase(context: Context) {
        this.context = context
        personDao = AppDataBase.getInstance(context)?.personDao()
        dogDao = AppDataBase.getInstance(context)?.dogDao()
        catDao = AppDataBase.getInstance(context)?.catDao()
    }

    suspend fun insert(any: Any) {
        when (any) {
            is Person -> {
                personDao?.insert(any)
            }
            is Dog -> {
                dogDao?.insert(any)
            }
            is Cat -> {
                catDao?.insert(any)
            }
        }
    }

    fun delete(any: Any) {
        when (any) {
            is Person -> {
                personDao?.delete(any)
            }
            is Dog -> {
                dogDao?.delete(any)
            }
            is Cat -> {
                catDao?.delete(any)
            }
        }
    }

    suspend fun <T> queryAll(t: T): List<Any>? {
        when (t) {
            is Person -> {
                return personDao?.getAllPerson()
            }
            is Dog -> {
                return dogDao?.getAllDog()
            }
            is Cat -> {
                return catDao?.getAllCatAndPerson()
            }
            else -> {
                return null
            }
        }
    }

    fun queryById(int: Int, any: Any): Any? {
        when (any) {
            is Person -> {
                return personDao?.getPersonById(int)
            }
            is Dog -> {
                return dogDao?.getDogById(int)
            }
            is Cat -> {
                return catDao?.getCatById(int)
            }
            else -> {
                return null
            }
        }
    }

    fun update(any: Any) {
        when (any) {
            is Person -> {
                personDao?.updatePerson(any)
            }
            is Dog -> {
                dogDao?.updateDog(any)
            }
            is Cat -> {
                catDao?.updateCat(any)
            }
        }

    }

    fun bind(id: Int, otherId: Int, any: Any) {
        var person = personDao?.getPersonById(id)
        person?.let {
            when (any) {
                is Dog -> { it.dogId = otherId }
                is Cat -> { person.catId = otherId }
            }
            personDao?.updatePerson(it)
        }
    }

    fun unbind(id: Int, any: Any?) {
        var person = personDao?.getPersonById(id)
        if (person != null) {
            when (any) {
                is Dog -> { person.dogId = null }
                is Cat -> { person.catId = null }
                else->{
                    person.dogId = null
                    person.catId = null
                }
            }
            personDao?.updatePerson(person)
        }
    }


    fun transaction(person: Person) {
        AppDataBase.getInstance(context)?.runInTransaction {

        }
    }

    @Transaction
    fun transaction2(person: Person) {

    }


    override fun onCleared() {
        super.onCleared()
    }

}