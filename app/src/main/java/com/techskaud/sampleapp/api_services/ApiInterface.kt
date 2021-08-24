package com.techskaud.sampleapp.api_services

import com.techskaud.sampleapp.response_model.AlbumModel
import com.techskaud.sampleapp.response_model.ApiResponse
import com.techskaud.sampleapp.response_model.DataModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("posts")
    suspend fun getData():List<DataModel>
    @GET("albums/1/photos")
    suspend fun getPhotos():ArrayList<AlbumModel>

    @GET("api/users")
    suspend fun getListData(@Query("page") pageNumber: Int): ApiResponse

    companion object {
        public fun getApiService() = Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

}