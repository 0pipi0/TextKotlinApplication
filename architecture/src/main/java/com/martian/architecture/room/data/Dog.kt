package com.martian.architecture.room.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Create by：Martian
 * on 2022/6/14
 * indices = [Index(value = ["id"], unique = true)]
 */
@Entity()
data class Dog(var name: String, var age: Int) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    constructor(id: Int) : this("", 0) {
        this.id = id
    }

    override fun toString(): String {
        return "Dog(name='$name', age=$age, id=$id)"
    }
}
