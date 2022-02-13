package com.manaois.spot1fy.artistDetails.models

import com.google.gson.annotations.SerializedName

data class ArtistAlbum(
    @SerializedName("idAlbum")
    val id: String,
    @SerializedName("strAlbum")
    val name: String,
    @SerializedName("intYearReleased")
    val year: Int,
    @SerializedName("strAlbumThumb")
    val thumbnail: String?
) {
}