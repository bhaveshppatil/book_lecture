package com.project.booklecture.di.repository

import com.project.booklecture.remote.ApiService
import com.project.booklecture.remote.Resource
import com.project.booklecture.remote.ResponseHandler
import com.project.booklecture.remote.response.ClassResponse
import com.project.booklecture.remote.response.ClassResponseItem
import javax.inject.Inject

class LectureRepo @Inject constructor(private val apiService : ApiService) {
    private val responseHandler : ResponseHandler = ResponseHandler()

    suspend fun getDataFromApi(): Resource<ClassResponse> {
        return try {
            val lectureResponse: ClassResponse = apiService.getLecturesData()
            responseHandler.handleSuccess(lectureResponse)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}