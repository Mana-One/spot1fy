package com.manaois.spot1fy.database

import android.content.Context
import androidx.room.Room

class DatabaseManager(context: Context) {
    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "db.sqlite"
    ).build()

    fun addLikedAlbum(album: LikedAlbum) {
        db.albumDao().add(album)
    }

    fun addLikedArtist(artist: LikedArtist) {
        db.artistDao().add(artist)
    }

    fun getLikedAlbums(): List<LikedAlbum> {
        return db.albumDao().getAll()
    }

    fun getLikedArtist(): List<LikedArtist> {
        return db.artistDao().getAll()
    }

    fun removeLikedAlbum(album: LikedAlbum) {
        db.albumDao().remove(album)
    }

    fun removeLikedArtist(artist: LikedArtist) {
        db.artistDao().remove(artist)
    }
}