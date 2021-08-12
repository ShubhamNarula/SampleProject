package com.techskaud.sampleapp.viewmodel

import android.content.Context
import androidx.hilt.Assisted
import androidx.lifecycle.*
import com.techskaud.sampleapp.datastore.DataStoreClass
import com.techskaud.sampleapp.repository.BaseRepository
import com.techskaud.sampleapp.response_model.AlbumModel
import com.techskaud.sampleapp.response_model.DataModel
import com.techskaud.sampleapp.utilities.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject
constructor(private val baseRepo: BaseRepository,
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<DataModel>> = MutableLiveData()

    val dataState: LiveData<DataState<DataModel>>
        get() = _dataState

    fun getData(
        mainStateEvent: MainStateEvent,
        context: Context
    ) {

        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.getDataEvents -> {
                    baseRepo.getData(context)
                        .onEach { dataState ->
                            _dataState.value = dataState


                        }
                        .launchIn(viewModelScope)
                }
            }
        }
    }

    private val _dataStateForPhoto: MutableLiveData<DataState<AlbumModel>> = MutableLiveData()

    val dataStateForPhoto: LiveData<DataState<AlbumModel>>
        get() = _dataStateForPhoto

    fun getPhotos(
        mainStateEvent: MainStateEvent,
        context: Context
    ) {

        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.getDataEvents -> {
                    baseRepo.getPhoto(context)
                        .onEach { dataState ->
                            _dataStateForPhoto.value = dataState

                        }
                        .launchIn(viewModelScope)
                }
            }
        }
    }


}



sealed class MainStateEvent{
    object getDataEvents: MainStateEvent()
}








