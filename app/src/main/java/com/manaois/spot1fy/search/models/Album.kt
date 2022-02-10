package com.manaois.spot1fy.search.models

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName(value = "strAlbum")
    val name: String,
    @SerializedName(value = "strArtist")
    val artist: String,
    @SerializedName(value = "strAlbumThumb")
    val thumbnail: String?
) {
}