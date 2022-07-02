package com.project.booklecture.di.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.project.booklecture.di.repository.LectureRepo
import com.project.booklecture.remote.Resource
import com.project.booklecture.remote.response.ClassResponse
import com.project.booklecture.remote.response.ClassResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class LectureViewModel @Inject constructor(private val lectureRepo: LectureRepo) : ViewModel(){

    fun getDataFromApi(): LiveData<Resource<ClassResponse>> {
        return liveData(Dispatchers.IO) {
            val data = lectureRepo.getDataFromApi()
            emit(data)
        }
    }


}