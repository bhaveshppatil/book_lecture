package com.project.booklecture.remote

import com.project.booklecture.remote.response.ClassResponse
import com.project.booklecture.remote.response.ClassResponseItem
import com.project.booklecture.utils.Constant.ENDPOINT
import retrofit2.http.GET

interface ApiService {

    @GET(ENDPOINT)
    suspend fun getLecturesData(): ClassResponse
}