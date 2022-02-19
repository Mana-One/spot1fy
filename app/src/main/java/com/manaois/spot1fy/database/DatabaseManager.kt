package com.manaois.spot1fy.database

import android.content.Context
import androidx.room.Room

class DatabaseManager(context: Context) {
    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "db.sqlite"
    ).build()

    fun addLikedAlbum(album: AlbumModel) {
        db.albumDao().add(album)
    }

    fun addLikedArtist(artist: ArtistModel) {
        db.artistDao().add(artist)
    }

    fun removeLikedAlbum(album: AlbumModel) {
        db.albumDao().remove(album)
    }

    fun removeLikedArtist(artist: ArtistModel) {
        db.artistDao().remove(artist)
    }
}