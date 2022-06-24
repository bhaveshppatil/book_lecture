package com.project.booklecture.adapter.clicklistener

import com.project.booklecture.remote.response.ClassResponseItem

interface OnItemClick {
    fun onLectureClick(classResponseItem: ClassResponseItem)

}