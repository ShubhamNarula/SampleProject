package com.techskaud.sampleapp.api_services

import com.techskaud.sampleapp.response_model.AlbumModel
import com.techskaud.sampleapp.response_model.DataModel
import retrofit2.http.GET

interface ApiInterface {
    @GET("posts")
    suspend fun getData():List<DataModel>
    @GET("albums/1/photos")
    suspend fun getPhotos():ArrayList<AlbumModel>

}