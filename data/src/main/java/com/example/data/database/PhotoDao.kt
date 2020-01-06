package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.PhotoEntity


@Dao
interface PhotoDao {
    @Query("SELECT * FROM photo")
    fun getAll():  List<PhotoEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(photo : PhotoEntity)

}