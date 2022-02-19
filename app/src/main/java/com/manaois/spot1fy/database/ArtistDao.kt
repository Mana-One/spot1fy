package com.manaois.spot1fy.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ArtistDao {
    @Query("SELECT * FROM ArtistModel")
    fun getAll(): List<ArtistModel>

    @Insert
    fun add(artist: ArtistModel)

    @Delete
    fun remove(artist: ArtistModel)
}