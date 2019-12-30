package com.example.roomapicli.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomapicli.model.Photo

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photo")
    fun getAll(): List<Photo>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(photos: List<Photo>)
}