package com.project.booklecture.adapter.clicklistener

import com.project.booklecture.remote.response.Product

interface OnItemClick {
    fun onLectureClick(product: Product)

}