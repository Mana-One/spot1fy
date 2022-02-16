package com.manaois.spot1fy.artistDetails.models

import com.google.gson.annotations.SerializedName

data class ArtistDetails(
    @SerializedName("idArtist")
    val id: String,
    @SerializedName("strArtist")
    val name: String,
    @SerializedName("strArtistThumb")
    val image: String,
    @SerializedName("strCountry")
    val locality: String,
    @SerializedName("strGenre")
    val genre: String,
    @SerializedName(value ="strBiographyEN")
    private val biographyEN: String,
    @SerializedName(value ="strBiographyFR")
    private val biographyFR: String?
) {
    fun getBiography(lang: String): String {
        val bio = when(lang) {
            "français", "French" -> biographyFR
            else -> biographyEN
        }
        return bio ?: biographyEN
    }
}