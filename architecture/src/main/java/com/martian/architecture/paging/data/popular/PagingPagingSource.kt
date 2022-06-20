package com.martian.architecture.paging.data.popular

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.martian.architecture.paging.data.Person

/**
 * Create by：Martian
 * on 2022/6/9
 */
class PagingPagingSource : PagingSource<Int, Person>() {
    val pageSize: Int = 10
    override fun getRefreshKey(state: PagingState<Int, Person>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Person> {
        try {
            val nextPageNumber = params.key ?: 1
            var list = mutableListOf<Person>()
            for (i in (nextPageNumber - 1) * pageSize until (nextPageNumber * pageSize)) {
                list.add(Person("PagingSource:$i", i))
            }
            return LoadResult.Page(list,params.key?.minus(1) ?: null,nextPageNumber+1)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}