package com.manaois.spot1fy.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AlbumDao {
    @Query("SELECT * FROM AlbumModel")
    fun getAll(): List<AlbumModel>

    @Insert
    fun add(album: AlbumModel)

    @Delete()
    fun remove(album: AlbumModel)
}