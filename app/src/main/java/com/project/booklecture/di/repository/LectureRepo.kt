package com.project.booklecture.di.repository

import com.project.booklecture.remote.ApiService
import com.project.booklecture.remote.Resource
import com.project.booklecture.remote.ResponseHandler
import com.project.booklecture.remote.response.LectureResponse
import java.lang.Exception
import javax.inject.Inject

class LectureRepo @Inject constructor(private val apiService : ApiService) {
    private val responseHandler : ResponseHandler = ResponseHandler()

    suspend fun getDataFromApi() : Resource<LectureResponse>{
        return  try {
            val lectureResponse : LectureResponse = apiService.getLecturesData()
            responseHandler.handleSuccess(lectureResponse)
        }catch (e : Exception){
            responseHandler.handleException(e)
        }
    }
}