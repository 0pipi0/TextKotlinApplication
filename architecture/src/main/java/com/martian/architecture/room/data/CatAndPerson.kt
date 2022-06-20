package com.martian.architecture.room.data

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Create by：Martian
 * on 2022/6/15
 */
data class CatAndPerson(@Embedded var cat:Cat,@Relation(parentColumn = "id",  entityColumn= "catId",) var person:Person){
    override fun toString(): String {
        return "CatAndPerson(cat=$cat, person=$person)"
    }
}
