package com.example.data.model

import com.example.data.domain.mapperRoom

class Photo(
    var id: Int?,
    var albumId: Int,
    var title: String?,
    var url: String?,
    var thumbnailUrl: String?
) : mapperRoom<PhotoEntity> {
    override fun mapToRoomModel() =
        PhotoEntity(id = id, albumId = albumId, title = title, url = url, thumbnailUrl = thumbnailUrl)
}