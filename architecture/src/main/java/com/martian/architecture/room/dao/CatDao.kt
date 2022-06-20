package com.martian.architecture.room.dao

import androidx.room.*
import com.martian.architecture.room.data.Cat
import com.martian.architecture.room.data.CatAndPerson

/**
 * Create by：Martian
 * on 2022/6/9
 */
@Dao
interface CatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cat: Cat)

    @Delete
    fun delete(cat: Cat)

    @Query("select * from Cat")
    fun getAllCat(): List<Cat>

    @Query("select * from Cat where id =:id")
    fun getCatById(id: Int): Cat

    @Update
    fun updateCat(cat: Cat)

    @Query("select * from Cat")
    fun getAllCatAndPerson(): List<CatAndPerson>


    @Query("select * from Cat where id =:id")
    fun getCatAndPersonById(id: Int): CatAndPerson



}