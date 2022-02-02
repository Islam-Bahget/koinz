package com.example.koinz.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PhotosRemoteKey")
class PhotosRemoteKey(
    @PrimaryKey
    val photosId: String,
    val prevKey: Int?,
    val nextKey: Int?

)