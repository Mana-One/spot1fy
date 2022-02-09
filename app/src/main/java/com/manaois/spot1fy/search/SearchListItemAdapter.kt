package com.manaois.spot1fy.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.manaois.spot1fy.R
import java.lang.Exception

class SearchListItemAdapter(
    private val context: Context,
    private val artistList: List<String> = List(5) { "test artist "},
    private val albumList: List<String> = List(5) { "test album" }
) : RecyclerView.Adapter<SearchListItemAdapter.SearchListItemViewHolder>() {

    companion object {
        const val ARTISTS_HEADER = 0
        const val ALBUMS_HEADER = 1
        const val ARTIST = 2
        const val ALBUM = 3
    }

    class SearchListItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindArtistsHeader() {
            view.findViewById<TextView>(R.id.list_header_title).text = "Artists"
        }

        fun bindAlbumsHeader() {
            view.findViewById<TextView>(R.id.list_header_title).text = "Albums"
        }

        fun bindArtist(data: String) {
            view.findViewById<TextView>(R.id.artist_item_title).text = data
        }

        fun bindAlbum(data: String) {
            view.findViewById<TextView>(R.id.album_item_artist).text = data
        }
    }

    override fun getItemCount(): Int {
        return 2 + artistList.size + albumList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListItemViewHolder {
        val adapterLayout = when(viewType) {
            ARTIST -> {
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.artist_item, parent, false)
            }
            ALBUM -> {
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.album_item, parent, false)
            }
            ARTISTS_HEADER, ALBUMS_HEADER -> {
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_header, parent, false)
            }
            else -> throw Exception("Invalid view type.")
        }
        return SearchListItemViewHolder(adapterLayout)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) ARTISTS_HEADER
            else if(position == artistList.size + 1) ALBUMS_HEADER
            else if (1 <= position && position <= artistList.size) ARTIST
            else if (artistList.size + 1 < position && position <= 1 + artistList.size + albumList.size) ALBUM
            else throw Exception("Invalid view type.")
    }

    override fun onBindViewHolder(holder: SearchListItemViewHolder, position: Int) {
        when(getItemViewType(position)) {
            ARTISTS_HEADER -> holder.bindArtistsHeader()
            ALBUMS_HEADER -> holder.bindAlbumsHeader()
            ARTIST -> holder.bindArtist(artistList[position - 1])
            ALBUM -> holder.bindAlbum(albumList[position - artistList.size - 2])
            else -> throw  Exception("Invalid view type.")
        }
    }
}