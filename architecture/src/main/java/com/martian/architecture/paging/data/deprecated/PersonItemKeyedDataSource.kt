package com.martian.architecture.paging.data.deprecated

import androidx.paging.ItemKeyedDataSource
import com.martian.architecture.paging.data.Person

/**
 * Create by：Martian
 * on 2022/6/8
 */
class PersonItemKeyedDataSource(private val pageSize: Int) : ItemKeyedDataSource<Int, Person>() {
    private var FIRST_PAGE = 1

    /**
     * 获取下一页的起始位置
     */
    override fun getKey(item: Person): Int {
        return item.age
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Person>) {
        val list = mutableListOf<Person>()
        for (i in 0..9) {
            list.add(Person("ItemKeyed:$i", i))
        }
        callback.onResult(list)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Person>) {
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Person>) {
        val list = mutableListOf<Person>()
        var startPosition = FIRST_PAGE++ * pageSize
        for (i in startPosition..(startPosition + (pageSize - 1))) {
            list.add(Person("ItemKeyed:$i", i))
        }
        callback.onResult(list)
    }


}