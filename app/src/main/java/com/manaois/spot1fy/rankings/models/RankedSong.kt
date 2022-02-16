package com.manaois.spot1fy.rankings.models

import com.google.gson.annotations.SerializedName

data class RankedSong(
    @SerializedName("idTrack")
    override val id: String,
    @SerializedName(value = "strTrack")
    override val name: String,
    @SerializedName(value = "strArtist")
    override val artist: String,
    @SerializedName(value = "strTrackThumb")
    override val thumbnail: String
) : RankedItem {
}