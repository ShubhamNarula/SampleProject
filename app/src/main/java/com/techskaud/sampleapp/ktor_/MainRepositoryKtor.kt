package com.techskaud.sampleapp.ktor_

import com.techskaud.sampleapp.response_model.DataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepositoryKtor @Inject
constructor(private val apiService: ApiServiceKtor) {

    fun getPost() : Flow<List<DataModel>> = flow {
        emit(apiService.getPost())
    }.flowOn(Dispatchers.IO)

}