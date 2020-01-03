package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.Photo


@Dao
interface PhotoDao {
    @Query("SELECT * FROM photo")
    fun getAll():  List<Photo>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(photo : Photo)

}