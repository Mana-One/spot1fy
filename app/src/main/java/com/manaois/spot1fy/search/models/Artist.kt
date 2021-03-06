package com.manaois.spot1fy.search.models

import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("idArtist")
    val id: String,
    @SerializedName(value = "strArtist")
    val name: String,
    @SerializedName(value = "strArtistThumb")
    val thumbnail: String?
) {
}