package com.manaois.spot1fy.artistDetails.models

import com.google.gson.annotations.SerializedName

data class ArtistPopularSong(
    @SerializedName("idTrack")
    val id: String,
    @SerializedName("strTrack")
    val name: String
) {
}