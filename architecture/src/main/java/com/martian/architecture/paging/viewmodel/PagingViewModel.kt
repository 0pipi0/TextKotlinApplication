package com.martian.architecture.paging.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.martian.architecture.paging.data.Person
import com.martian.architecture.paging.data.PersonFactory
import com.martian.architecture.paging.data.popular.PagingPagingSource

/**
 * Create by：Martian
 * on 2022/6/8
 */
class PagingViewModel :ViewModel(){

    var personList:LiveData<PagedList<Person>>? = null
    private var personDataSource:DataSource<Int,Person>? = null
    private val pageSize :Int= 30
    private val totalCount :Int= 70
    private val prefetchDistance :Int= 5
    init {
        val personFactory = PersonFactory(pageSize,totalCount)
        personDataSource = personFactory.create()
        val config = PagedList.Config.Builder().setEnablePlaceholders(true).setPageSize(pageSize).setPrefetchDistance(prefetchDistance).build()
        personList = LivePagedListBuilder(personFactory,config).build()
    }

    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = 20)
    ) {
        PagingPagingSource()
    }.flow
        .cachedIn(viewModelScope)

    fun invalidateDataSource(){
        personDataSource!!.invalidate()
    }

    override fun onCleared() {
        super.onCleared()
    }


}