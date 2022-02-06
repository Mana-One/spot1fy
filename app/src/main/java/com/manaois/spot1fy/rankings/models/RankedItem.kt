package com.manaois.spot1fy.rankings.models

class RankedItem(
    val name: String,
    val artist: String,
    val thumbnail: String
) {
    companion object {
        fun mockSongs() = List<RankedItem>(10) {
            RankedItem("Gucci Gang", "Lil Pump", "https://i1.sndcdn.com/artworks-000243000404-miq00m-t500x500.jpg")
        }

        fun mockAlbums() = List<RankedItem>(10) {
            RankedItem("After hours", "Khalid", "https://cdns-images.dzcdn.net/images/cover/fd00ebd6d30d7253f813dba3bb1c66a9/264x264.jpg")
        }
    }
}