package com.manaois.spot1fy.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ArtistDao {
    @Query("SELECT * FROM LikedArtist")
    fun getAll(): List<LikedArtist>

    @Insert
    fun add(artist: LikedArtist)

    @Delete
    fun remove(artist: LikedArtist)
}