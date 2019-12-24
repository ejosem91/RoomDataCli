package com.example.roomapicli.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo")
data class Photo(
    @PrimaryKey var id : Int,
    var albumId : Int?,
    var title : String?,
    var url : String?,
    var thumbnailUrl : String?
)