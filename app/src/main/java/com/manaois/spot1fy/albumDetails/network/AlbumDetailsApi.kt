package com.manaois.spot1fy.albumDetails.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.manaois.spot1fy.albumDetails.models.AlbumDetails
import com.manaois.spot1fy.albumDetails.models.AlbumSong
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class AlbumDetailsResponse(
    val album: List<AlbumDetails>?
)

data class AlbumSongsResponse(
    val track: List<AlbumSong>?
)

interface AlbumDetailsApi {
    @GET("album.php")
    fun getAlbumDetailsAsync(
        @Query("m") id: String
    ): Deferred<AlbumDetailsResponse>

    @GET("track.php")
    fun getAlbumSongsAsync(
        @Query("m") albumId: String
    ): Deferred<AlbumSongsResponse>
}

object AlbumDetailsRequest {
    private val api = Retrofit.Builder()
        .baseUrl("https://theaudiodb.com/api/v1/json/523532/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(AlbumDetailsApi::class.java)

    suspend fun getAlbumDetails(id: String): AlbumDetails {
        val result = api.getAlbumDetailsAsync(id).await()
        if (result.album == null || result.album.isEmpty()) {
            throw Exception("Album not found.")
        }
        return result.album[0]
    }

    suspend fun getAlbumSongs(albumId: String): List<AlbumSong> {
        val result = api.getAlbumSongsAsync(albumId).await()
        return result.track ?: listOf()
    }
}