package com.manaois.spot1fy.rankings.models

import com.google.gson.annotations.SerializedName

data class RankedAlbum(
    @SerializedName(value = "strAlbum")
    override val name: String,
    @SerializedName(value = "strArtist")
    override val artist: String,
    @SerializedName(value = "strAlbumThumb")
    override val thumbnail: String?
): RankedItem {
}