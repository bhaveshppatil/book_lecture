package com.project.booklecture.remote.response

import java.io.Serializable

data class ClassResponseItem(
    val ac_nonac: String,
    val address: String,
    val classname: String,
    val contact: String,
    val date: String,
    val density: String,
    val `field`: String,
    val images: String,
    val language: String,
    val logo: String,
    val size: String,
    val time: String
) : Serializable