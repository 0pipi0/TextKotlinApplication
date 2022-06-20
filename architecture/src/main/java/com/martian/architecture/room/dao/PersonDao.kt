package com.martian.architecture.room.dao

import androidx.room.*
import com.martian.architecture.room.data.Person

/**
 * Create by：Martian
 * on 2022/6/9
 */
@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(person:Person)

    @Delete
    fun delete(person:Person)

    @Query("select * from Person")
    fun getAllPerson():List<Person>

    @Query("select * from Person where id =:id")
    fun getPersonById(id:Int):Person

    @Update
    fun updatePerson(person:Person)

}