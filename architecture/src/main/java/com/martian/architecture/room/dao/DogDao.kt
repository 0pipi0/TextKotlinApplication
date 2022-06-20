package com.martian.architecture.room.dao

import androidx.room.*
import com.martian.architecture.room.data.Dog

/**
 * Create by：Martian
 * on 2022/6/9
 */
@Dao
interface DogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dog: Dog)

    @Delete
    fun delete(dog: Dog)

    @Query("select * from Dog")
    fun getAllDog():List<Dog>

    @Query("select * from Dog where id =:id")
    fun getDogById(id:Int):Dog

    @Update
    fun updateDog(dog: Dog)

}