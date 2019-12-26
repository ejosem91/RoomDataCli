package com.example.roomapicli.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface PhotoDao {
    @Query("SELECT * FROM photo")
    fun getAll():  List<Photo>

    @Insert
    fun insert(photo : Photo)

}