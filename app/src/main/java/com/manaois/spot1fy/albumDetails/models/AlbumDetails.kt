package com.manaois.spot1fy.albumDetails.models

import com.google.gson.annotations.SerializedName

data class AlbumDetails(
    @SerializedName("idAlbum")
    val id: String,
    @SerializedName("strAlbum")
    val name: String,
    @SerializedName("strArtist")
    val artist: String,
    @SerializedName("strAlbumThumb")
    val thumbnail: String?,
    @SerializedName("strDescriptionEN")
    private val descriptionEN: String,
    @SerializedName("strDescriptionFR")
    private val descriptionFR: String?,
    @SerializedName("intScore")
    val score: Double,
    @SerializedName("intScoreVotes")
    val votes: Int
) {
    fun getDescription(lang: String): String {
        val description = when(lang) {
            "français", "French" -> descriptionFR
            else -> descriptionEN
        }
        return description ?: descriptionEN
    }
}