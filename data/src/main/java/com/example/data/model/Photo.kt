package com.example.data.model

import com.example.data.domain.MapperRoom

class Photo(
    var id: Int?,
    var albumId: Int,
    var title: String?,
    var url: String?,
    var thumbnailUrl: String?
) : MapperRoom<PhotoEntity> {
    override fun mapToRoomModel() =
        PhotoEntity(id = id, albumId = albumId, title = title, url = url, thumbnailUrl = thumbnailUrl)
}