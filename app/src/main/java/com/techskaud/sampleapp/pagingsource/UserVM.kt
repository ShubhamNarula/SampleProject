package com.techskaud.sampleapp.pagingsource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.techskaud.sampleapp.api_services.ApiInterface
import com.techskaud.sampleapp.repository.SignUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject



public class UserVM(apiInterface: ApiInterface) : ViewModel() {


    val listData = Pager(PagingConfig(pageSize = 6)) {
        UserPagingSource(apiInterface)
    }.flow.cachedIn(viewModelScope)

}