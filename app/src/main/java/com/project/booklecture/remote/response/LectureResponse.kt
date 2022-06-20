package com.project.booklecture.remote.response

data class LectureResponse(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)