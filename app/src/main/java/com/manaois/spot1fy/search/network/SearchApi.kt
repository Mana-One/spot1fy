package com.manaois.spot1fy.search.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.manaois.spot1fy.common.APIInterceptor
import com.manaois.spot1fy.search.models.Album
import com.manaois.spot1fy.search.models.Artist
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class SearchArtistsApiResponse(
    val artists: List<Artist>?
)

data class SearchAlbumsApiResponse(
    val album: List<Album>?
)

interface SearchApi {
    @GET("search.php")
    fun searchArtistsAsync(
        @Query("s") s: String
    ): Deferred<SearchArtistsApiResponse>

    @GET("searchalbum.php")
    fun searchAlbumsAsync(
        @Query("s") s: String
    ): Deferred<SearchAlbumsApiResponse>
}

object SearchApiRequest {
    private val api = Retrofit.Builder()
        .baseUrl("https://theaudiodb.com/api/v1/json/_API_KEY_/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(
            OkHttpClient().newBuilder()
                .addInterceptor(APIInterceptor())
                .build()
        )
        .build()
        .create(SearchApi::class.java)

    suspend fun searchArtists(input: String): List<Artist>? {
        val result = api.searchArtistsAsync(input).await()
        return result.artists
    }

    suspend fun searchAlbums(input: String): List<Album>? {
        val result = api.searchAlbumsAsync(input).await()
        return result.album
    }
}