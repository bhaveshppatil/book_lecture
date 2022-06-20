package com.project.booklecture.remote

import com.project.booklecture.remote.response.LectureResponse
import com.project.booklecture.utils.Constant.ENDPOINT
import retrofit2.http.GET

interface ApiService {

    @GET(ENDPOINT)
    suspend fun getLecturesData() : LectureResponse
}