package com.example.roomapicli.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo")
class Photo(
    @PrimaryKey
    var id: Int?,
    var albumId: Int,
    var title: String?,
    var url: String?,
    var thumbnailUrl: String?
) : Parcelable {

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

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Photo> {
        override fun createFromParcel(parcel: Parcel) = Photo(parcel)

        override fun newArray(size: Int): Array<Photo?> = arrayOfNulls(size)
    }
}