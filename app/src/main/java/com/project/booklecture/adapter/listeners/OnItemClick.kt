package com.project.booklecture.adapter.listeners

import com.project.booklecture.remote.response.ClassResponseItem

interface OnItemClick {
    fun onLectureClick(classResponseItem: ClassResponseItem)

}