package com.manaois.spot1fy.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AlbumDao {
    @Query("SELECT * FROM LikedAlbum")
    fun getAll(): List<LikedAlbum>

    @Query( "SELECT * FROM LikedAlbum WHERE apiId=:apiId")
    fun find(apiId: String): LikedAlbum?

    @Insert
    fun add(album: LikedAlbum)

    @Delete()
    fun remove(album: LikedAlbum)
}