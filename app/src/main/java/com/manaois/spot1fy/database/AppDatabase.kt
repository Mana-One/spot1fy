package com.manaois.spot1fy.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LikedAlbum::class, LikedArtist::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao
    abstract fun artistDao(): ArtistDao
}