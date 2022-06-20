package com.martian.architecture.paging.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.martian.architecture.paging.data.deprecated.PersonItemKeyedDataSource
import com.martian.architecture.paging.data.deprecated.PersonPageKeyedDataSource
import com.martian.architecture.paging.data.deprecated.PersonPositionDataSource

/**
 * Create by：Martian
 * on 2022/6/8
 */
class PersonFactory (val pageSize:Int,val totalCount:Int):DataSource.Factory<Int,Person>() {
    private var sourceLiveDataPageKeyed:MutableLiveData<PersonPageKeyedDataSource> = MutableLiveData()
    private var sourceLiveDataItemKeyed:MutableLiveData<PersonItemKeyedDataSource> = MutableLiveData()
    private var sourceLiveDataPosition:MutableLiveData<PersonPositionDataSource> = MutableLiveData()
    override fun create(): DataSource<Int, Person> {
//        val personDataSourcePageKeyed = PersonPageKeyedDataSource(pageSize)
//        sourceLiveDataPageKeyed.postValue(personDataSourcePageKeyed)
//
//        val personDataSourceItemKeyed = PersonItemKeyedDataSource(pageSize)
//        sourceLiveDataItemKeyed.postValue(personDataSourceItemKeyed)

        val personDataSourcePosition = PersonPositionDataSource(pageSize,totalCount)
//        sourceLiveDataPosition.postValue(personDataSourcePosition)
        return personDataSourcePosition
    }
}