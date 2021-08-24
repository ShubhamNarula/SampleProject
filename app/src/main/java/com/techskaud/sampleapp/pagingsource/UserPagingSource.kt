package com.techskaud.sampleapp.pagingsource

import androidx.paging.PagingSource
import com.techskaud.sampleapp.api_services.ApiInterface
import com.techskaud.sampleapp.response_model.Data


class UserPagingSource(
    val apiInterface: ApiInterface,
) : PagingSource<Int, Data>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        try {
            // Start refresh at page 1 if undefined.
            val nextPage = params.key ?: 1
            val response = apiInterface.getListData(nextPage)

            return LoadResult.Page(
                data = response.data?: emptyList(),
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = response.page?.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}