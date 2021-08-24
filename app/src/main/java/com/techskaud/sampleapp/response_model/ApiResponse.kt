package com.techskaud.sampleapp.response_model

data class ApiResponse(
    var `data`: List<Data>?,
    var page: Int?,
    var per_page: Int?,
    var total: Int?,
    var total_pages: Int?
)