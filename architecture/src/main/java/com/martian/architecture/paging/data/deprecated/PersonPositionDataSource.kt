package com.martian.architecture.paging.data.deprecated

import androidx.paging.PositionalDataSource
import com.martian.architecture.paging.data.Person

/**
 * Create by：Martian
 * on 2022/6/8
 */
class PersonPositionDataSource(private val pageSize: Int, private val totalCount: Int) : PositionalDataSource<Person>() {
    /**
     * 加载第一页数据
     */
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Person>) {
        val list = mutableListOf<Person>()
        for (i in 0 until pageSize) {
            list.add(Person("Position:$i", i))
        }
        callback.onResult(list, 0, totalCount)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Person>) {
        val list = mutableListOf<Person>()
        val until = if((params.startPosition + params.loadSize)>=totalCount) totalCount else ( params.startPosition + params.loadSize)
        for (i in params.startPosition until until) {
            list.add(Person("Position:$i", i))
        }
        callback.onResult(list)
    }
}