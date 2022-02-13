package com.manaois.spot1fy.artistDetails.models

import com.google.gson.annotations.SerializedName

data class ArtistDetails(
    @SerializedName("idArtist")
    val id: String,
    @SerializedName("strArtist")
    val name: String,
    @SerializedName("strCountry")
    val locality: String,
    @SerializedName("strGenre")
    val genre: String,
    @SerializedName("strBiographyEN")
    val biography: String
) {
}