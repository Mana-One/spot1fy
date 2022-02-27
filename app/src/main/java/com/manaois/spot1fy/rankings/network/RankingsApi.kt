package com.manaois.spot1fy.rankings.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.manaois.spot1fy.common.APIInterceptor
import com.manaois.spot1fy.rankings.models.RankedAlbum
import com.manaois.spot1fy.rankings.models.RankedItem
import com.manaois.spot1fy.rankings.models.RankedSong
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// URL https://theaudiodb.com/api/v1/json/523532/trending.php?country=us&type=itunes&format=albums
data class RankingsSongsApiResponse(
    val trending: List<RankedSong>
)

data class RankingsAlbumsApiResponse(
    val trending: List<RankedAlbum>
)

interface RankingsApi {
    @GET("trending.php")
    fun getRankedSongs(
        @Query("country") country: String = "us",
        @Query("type") type: String = "itunes",
        @Query("format") format: String = "singles"
    ): Deferred<RankingsSongsApiResponse>


    @GET("trending.php")
    fun getRankedAlbums(
        @Query("country") country: String = "us",
        @Query("type") type: String = "itunes",
        @Query("format") format: String = "albums"
    ): Deferred<RankingsAlbumsApiResponse>
}

object RankingsApiRequest {
    // private var c = 0
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
        .create(RankingsApi::class.java)

    suspend fun getRankedSongs(): List<RankedItem> {
        /*if (c == 0) {
            c++
            throw Exception("hey")
        }*/
        val result = api.getRankedSongs().await()
        return result.trending
    }

    suspend fun getRankedAlbums(): List<RankedItem> {
        val result = api.getRankedAlbums().await()
        return result.trending
    }
}

