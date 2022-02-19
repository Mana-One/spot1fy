package com.manaois.spot1fy.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AlbumDao {
    @Query("SELECT * FROM LikedAlbum")
    fun getAll(): List<LikedAlbum>

    @Insert
    fun add(album: LikedAlbum)

    @Delete()
    fun remove(album: LikedAlbum)
}