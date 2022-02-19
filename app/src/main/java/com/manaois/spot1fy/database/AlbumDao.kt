package com.manaois.spot1fy.database

import androidx.room.*

@Dao
interface AlbumDao {
    @Query("SELECT * FROM LikedAlbum")
    fun getAll(): List<LikedAlbum>

    @Query( "SELECT * FROM LikedAlbum WHERE apiId=:apiId")
    fun find(apiId: String): LikedAlbum?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun add(album: LikedAlbum)

    @Delete()
    fun remove(album: LikedAlbum)
}