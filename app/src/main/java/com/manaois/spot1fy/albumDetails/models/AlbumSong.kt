package com.manaois.spot1fy.albumDetails.models

import com.google.gson.annotations.SerializedName

data class AlbumSong(
    @SerializedName("idTrack")
    val id: String,
    @SerializedName("strTrack")
    val name: String
) {
}