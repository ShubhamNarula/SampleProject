package com.techskaud.sampleapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techskaud.sampleapp.datastore.DataStoreClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SaveDataViewModel @Inject
constructor(private val datastoreSetting: DataStoreClass) : ViewModel() {


    fun saveToLocal(modeType: String) = viewModelScope.launch {
        datastoreSetting.saveToLocal(modeType)
    }

    val readFromLocal = datastoreSetting.readFromLocal
}