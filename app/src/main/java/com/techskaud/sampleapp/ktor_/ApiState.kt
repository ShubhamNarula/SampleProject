package com.techskaud.sampleapp.ktor_

import com.techskaud.sampleapp.response_model.DataModel

sealed class ApiState{
    object Loading : ApiState()
    object Empty : ApiState()
    class Success(val response: List<DataModel>) : ApiState()
    class Failure(val error:Throwable) : ApiState()
}
