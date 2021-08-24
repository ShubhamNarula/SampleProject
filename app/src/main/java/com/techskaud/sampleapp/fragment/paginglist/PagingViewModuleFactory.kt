package com.techskaud.sampleapp.fragment.paginglist



import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.techskaud.sampleapp.api_services.ApiInterface
import com.techskaud.sampleapp.pagingsource.UserVM

class PagingViewModuleFactory(private val apiService: ApiInterface) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserVM::class.java)) {
            return UserVM(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}