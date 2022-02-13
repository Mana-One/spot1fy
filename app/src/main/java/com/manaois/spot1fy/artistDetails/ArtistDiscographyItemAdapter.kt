package com.manaois.spot1fy.artistDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.manaois.spot1fy.R

class ArtistDiscographyItemAdapter(

): RecyclerView.Adapter<ArtistDiscographyItemAdapter.ArtistDiscographyItemViewHolder>() {
    companion object {
        const val ALBUMS_HEADER = 0
        const val POPULAR_SONGS_HEADER = 1
    }

    class ArtistDiscographyItemViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        fun bindAlbumsHeader() {
            view.findViewById<TextView>(R.id.list_header_title).text = "Albums (0)"
        }

        fun bindPopularSongsHeader() {
            view.findViewById<TextView>(R.id.list_header_title).text = "Most popular songs"
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArtistDiscographyItemViewHolder {
        val layoutId = when(viewType) {
            ALBUMS_HEADER, POPULAR_SONGS_HEADER -> R.layout.list_header
            else -> throw Exception("Invalid view type.")
        }
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(layoutId, parent, false)
        return ArtistDiscographyItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ArtistDiscographyItemViewHolder, position: Int) {
        when(getItemViewType(position)) {
            ALBUMS_HEADER -> holder.bindAlbumsHeader()
            POPULAR_SONGS_HEADER -> holder.bindPopularSongsHeader()
            else -> throw Exception("Invalid viex type.")
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun getItemViewType(position: Int): Int {
        return when(position){
            0 -> ALBUMS_HEADER
            1 -> POPULAR_SONGS_HEADER
            else -> throw Exception("Invalid view type.")
        }
    }
}