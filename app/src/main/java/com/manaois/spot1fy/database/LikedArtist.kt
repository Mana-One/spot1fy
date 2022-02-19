package com.manaois.spot1fy.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LikedArtist(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val apiId: String,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val thumbnail: String?
) {
}