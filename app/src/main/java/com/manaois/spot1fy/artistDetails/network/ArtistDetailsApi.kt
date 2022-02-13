package com.manaois.spot1fy.artistDetails.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.manaois.spot1fy.artistDetails.models.ArtistAlbum
import com.manaois.spot1fy.artistDetails.models.ArtistDetails
import com.manaois.spot1fy.artistDetails.models.ArtistPopularSong
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.lang.Exception

data class ArtistDetailsResponse(
    val artists: List<ArtistDetails>?
)

data class ArtistAlbumsResponse(
    val album: List<ArtistAlbum>?
)

data class ArtistPopularSongsResponse(
    val track: List<ArtistPopularSong>?
)

interface ArtistDetailsApi {
    @GET("artist.php")
    fun getArtistDetailsAsync(
        @Query("i") id: String
    ): Deferred<ArtistDetailsResponse>

    @GET("album.php")
    fun getArtistAlbumsAsync(
        @Query("i") artistId: String
    ): Deferred<ArtistAlbumsResponse>

    @GET("track-top10.php")
    fun getArtistPopularSongsAsync(
        @Query("s") artistName: String
    ): Deferred<ArtistPopularSongsResponse>
}

object ArtistDetailsApiRequest {
    private val api = Retrofit.Builder()
        .baseUrl("https://theaudiodb.com/api/v1/json/523532/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(ArtistDetailsApi::class.java)

    suspend fun getArtistDetails(id: String): ArtistDetails {
        val result = api.getArtistDetailsAsync(id).await()
        if (result.artists == null || result.artists.isEmpty()) {
            throw Exception("Artist not found.")
        }
        return result.artists[0]
    }

    suspend fun getArtistAlums(artistId: String): List<ArtistAlbum> {
        val result = api.getArtistAlbumsAsync(artistId).await()
        if (result.album == null) {
            throw Exception("Could not retrieve albums.")
        }
        return result.album
    }

    suspend fun getArtistPopularSongs(artistName: String): List<ArtistPopularSong> {
        val result = api.getArtistPopularSongsAsync(artistName).await()
        if (result.track == null) {
            throw Exception("Could not retrieve popular songs.")
        }
        return result.track
    }
}