package com.example.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.model.Photo


@Database(entities = [Photo::class], version = 1, exportSchema = false)
abstract class PhotoDataBase : RoomDatabase() {
    abstract  fun photoDao() : PhotoDao

    companion object {
        private var INSTANCE: PhotoDataBase? = null
        fun getInstance(context: Context): PhotoDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext
                        , PhotoDataBase::class.java, "photo_database"
                    ).build()
                }
                return instance
            }
        }
    }
}