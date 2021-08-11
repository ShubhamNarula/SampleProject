package com.techskaud.sampleapp.repository

import android.content.Context
import com.techskaud.sampleapp.api_services.ApiInterface
import com.techskaud.sampleapp.response_model.DataModel
import com.techskaud.sampleapp.utilities.DataState
import com.techskaud.sampleapp.viewmodel.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class BaseRepository
constructor(private val apiServiceImpl: ApiInterface) {
    suspend fun getData(
        context: Context
    ): Flow<DataState<DataModel>> =
        flow {
            delay(500)
            emit(DataState.Loading(context))
            try {
                val listData = apiServiceImpl.getData()
                emit(DataState.Success(listData))
            } catch (e: Exception) {
                emit(DataState.Error(e, context))
            }
        }.catch {
            println(it)
        }

}