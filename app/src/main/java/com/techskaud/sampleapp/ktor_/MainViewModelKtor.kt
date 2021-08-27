package com.techskaud.sampleapp.ktor_

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModelKtor
@Inject
constructor(private val mainRepository: MainRepositoryKtor): ViewModel() {

    private val _apiStateFlow: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)
    val apiStateFlow : StateFlow<ApiState> = _apiStateFlow

    fun getPost() = viewModelScope.launch {
        mainRepository.getPost()
            .onStart {
                _apiStateFlow.value = ApiState.Loading
            }
            .catch { e->
                _apiStateFlow.value = ApiState.Failure(e)
            }.collect { response->
                _apiStateFlow.value = ApiState.Success(response)
            }
    }
}