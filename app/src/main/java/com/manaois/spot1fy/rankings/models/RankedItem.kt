package com.manaois.spot1fy.rankings.models

class RankedItem(
    val name: String,
    val artist: String,
    val thumbnail: String
) {
    companion object {
        fun mockSongs() = List<RankedItem>(10) {
            RankedItem("Gucci Gang", "Lil Pump", "some url")
        }

        fun mockAlbums() = List<RankedItem>(10) {
            RankedItem("After hours", "Khalid", "some url")
        }
    }
}