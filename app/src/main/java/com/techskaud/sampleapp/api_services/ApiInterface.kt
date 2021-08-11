package com.techskaud.sampleapp.api_services

import com.techskaud.sampleapp.response_model.DataModel
import retrofit2.http.GET

interface ApiInterface {
    @GET("posts")
    suspend fun getData():List<DataModel>
}