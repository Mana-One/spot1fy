package com.manaois.spot1fy.artistDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.manaois.spot1fy.R
import com.manaois.spot1fy.artistDetails.models.ArtistAlbum
import com.manaois.spot1fy.artistDetails.models.ArtistPopularSong
import com.squareup.picasso.Picasso

class ArtistDiscographyItemAdapter(
    private val albums: List<ArtistAlbum>,
    private val popularSongs: List<ArtistPopularSong>
): RecyclerView.Adapter<ArtistDiscographyItemAdapter.ArtistDiscographyItemViewHolder>() {
    companion object {
        const val ALBUMS_HEADER = 0
        const val POPULAR_SONGS_HEADER = 1
        const val ALBUM = 2
        const val POPULAR_SONG = 3
    }

    class ArtistDiscographyItemViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        fun bindAlbumsHeader(count: Int) {
            view.findViewById<TextView>(R.id.list_header_title).text = "Albums ($count)"
        }

        fun bindPopularSongsHeader() {
            view.findViewById<TextView>(R.id.list_header_title).text = "Most popular songs"
        }

        fun bindAlbum(data: ArtistAlbum) {
            view.findViewById<TextView>(R.id.album_item_title).text = data.name
            view.findViewById<TextView>(R.id.album_item_artist).text = "${data.year}"
            if (data.thumbnail != null) {
                val thumbnail: ImageView = view.findViewById(R.id.album_item_thumbnail)
                Picasso.get().load(data.thumbnail).into(thumbnail)
            }
        }

        fun bindPopularSong(data: ArtistPopularSong, position: Int) {
            view.findViewById<TextView>(R.id.song_item_rank).text = "$position"
            view.findViewById<TextView>(R.id.song_item_title).text = data.name
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArtistDiscographyItemViewHolder {
        val layoutId = when(viewType) {
            ALBUMS_HEADER, POPULAR_SONGS_HEADER -> R.layout.list_header
            ALBUM -> R.layout.album_item
            POPULAR_SONG -> R.layout.song_item
            else -> throw Exception("Invalid view type.")
        }
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(layoutId, parent, false)
        return ArtistDiscographyItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ArtistDiscographyItemViewHolder, position: Int) {
        when(getItemViewType(position)) {
            ALBUMS_HEADER -> holder.bindAlbumsHeader(albums.size)
            POPULAR_SONGS_HEADER -> holder.bindPopularSongsHeader()
            ALBUM -> holder.bindAlbum(albums[position - 1])
            POPULAR_SONG -> holder.bindPopularSong(
                popularSongs[position - albums.size - 2],
                position - albums.size - 2
            )
            else -> throw Exception("Invalid view type.")
        }
    }

    override fun getItemCount(): Int {
        return 2 + albums.size + popularSongs.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) ALBUMS_HEADER
            else if (position == albums.size + 1) POPULAR_SONGS_HEADER
            else if (1 <= position && position <= albums.size) ALBUM
            else if (1 + albums.size < position && position <= 1 + albums.size + popularSongs.size) POPULAR_SONG
            else throw Exception("Invalid view type.")
    }
}