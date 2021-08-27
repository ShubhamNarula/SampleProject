package com.techskaud.sampleapp.ktor_

import com.techskaud.sampleapp.response_model.DataModel
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import javax.inject.Inject

class ApiServiceKtor  @Inject constructor() {

    private val client = HttpClient(Android){
        install(DefaultRequest){
            headers.append("Content-Type", "application/json")
        }
        install(JsonFeature){
            serializer = GsonSerializer()
        }
        engine {
            connectTimeout = 100_000
            socketTimeout = 100_000
        }
    }

    suspend fun getPost() : List<DataModel> {
        return client.get {
            url("https://jsonplaceholder.typicode.com/posts")
        }
    }
}