package com.example.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.model.Photo
import com.example.data.domain.mapperDomain

@Entity(tableName = "photo")
class PhotoEntity(
    @PrimaryKey
    var id: Int?,
    var albumId: Int,
    var title: String?,
    var url: String?,
    var thumbnailUrl: String?
) : Parcelable, mapperDomain<Photo> {
    override fun mapToDomainModel() =
        Photo(id = id, albumId = albumId, title =  title, url = url, thumbnailUrl = thumbnailUrl)


    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeInt(albumId)
        parcel.writeString(title)
        parcel.writeString(url)
        parcel.writeString(thumbnailUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PhotoEntity> {
        override fun createFromParcel(parcel: Parcel): PhotoEntity {
            return PhotoEntity(parcel)
        }

        override fun newArray(size: Int): Array<PhotoEntity?> {
            return arrayOfNulls(size)
        }
    }
}