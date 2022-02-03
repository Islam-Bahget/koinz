package com.example.koinz.data


import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "photos")
data class Photo(
    @field:SerializedName("owner")
    val owner: String? = null,

    var url: String? = null,

    @field:SerializedName("server")
    val server: String? = null,

    @field:SerializedName("ispublic")
    val ispublic: Int? = null,

    @field:SerializedName("isfriend")
    val isfriend: Int? = null,

    @field:SerializedName("farm")
    val farm: Int? = null,

    @PrimaryKey
    @Nullable
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("secret")
    val secret: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("isfamily")
    val isfamily: Int? = null
)
