package com.example.koinz.model

import com.example.koinz.data.Photo
import com.google.gson.annotations.SerializedName


data class PhotosResponse(

    @field:SerializedName("stat")
    val stat: String? = null,

    @field:SerializedName("photos")
    val photos: Photos? = null
)


data class Photos(

    @field:SerializedName("perpage")
    val perpage: Int? = null,

    @field:SerializedName("total")
    val total: Int? = null,

    @field:SerializedName("pages")
    val pages: Int? = null,

    @field:SerializedName("photo")
    val photo: List<Photo>? = null,

    @field:SerializedName("page")
    val page: Int? = null
)
