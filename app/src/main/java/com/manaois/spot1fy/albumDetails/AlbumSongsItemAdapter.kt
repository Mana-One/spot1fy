package com.manaois.spot1fy.albumDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.manaois.spot1fy.R
import com.manaois.spot1fy.albumDetails.models.AlbumSong

class AlbumSongsItemAdapter(
    private val songs: List<AlbumSong>
) : RecyclerView.Adapter<AlbumSongsItemAdapter.AlbumSongsItemViewHolder>() {
    companion object {
        const val SONGS_HEADER = 0
        const val SONG = 1
    }

    class AlbumSongsItemViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        fun bindSongsHeader() {
            view.findViewById<TextView>(R.id.list_header_title).text = "Songs"
        }

        fun bindSong(data: AlbumSong, rank: Int) {
            view.findViewById<TextView>(R.id.song_item_rank).text = "$rank"
            view.findViewById<TextView>(R.id.song_item_title).text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumSongsItemViewHolder {
        val layoutId = when(viewType) {
            SONGS_HEADER -> R.layout.list_header
            SONG -> R.layout.song_item
            else -> throw Exception("Invalid view type.")
        }
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(layoutId, parent, false)
        return AlbumSongsItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: AlbumSongsItemViewHolder, position: Int) {
        when(getItemViewType(position)) {
            SONGS_HEADER -> holder.bindSongsHeader()
            SONG -> holder.bindSong(songs[position - 1], position)
            else -> throw Exception("Invalid view type.")
        }
    }

    override fun getItemCount(): Int {
        return 1 + songs.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) SONGS_HEADER
        else if(1 <= position && position <= songs.size) SONG
        else throw Exception("Invalid view type.")
    }
}