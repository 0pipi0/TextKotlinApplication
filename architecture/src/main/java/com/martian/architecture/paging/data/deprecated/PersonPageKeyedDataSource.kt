package com.martian.architecture.paging.data.deprecated

import androidx.paging.PageKeyedDataSource
import com.martian.architecture.paging.data.Person

/**
 * Create by：Martian
 * on 2022/6/8
 */
class PersonPageKeyedDataSource(private val pageSize:Int): PageKeyedDataSource<Int, Person>() {
    var FIRST_PAGE = 1
//    val PAGE_SIZE = 10

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Person>) {
        val list = mutableListOf<Person>()
        for (i in 0 .. 9){
            list.add(Person("PageKeyed:$i",i))
        }
        callback.onResult(list,null,FIRST_PAGE)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Person>) {
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Person>) {
        val list = mutableListOf<Person>()
        var startPosition  = FIRST_PAGE * pageSize
        for (i in startPosition .. (startPosition +(pageSize-1))){
            list.add(Person("PageKeyed:$i",i))
        }
        callback.onResult(list,FIRST_PAGE++)
    }

}