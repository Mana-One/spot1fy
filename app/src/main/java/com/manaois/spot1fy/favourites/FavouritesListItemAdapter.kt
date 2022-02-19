package com.manaois.spot1fy.favourites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.manaois.spot1fy.R
import com.manaois.spot1fy.database.LikedAlbum
import com.manaois.spot1fy.database.LikedArtist
import com.squareup.picasso.Picasso

class FavouritesListItemAdapter(
    private val favouriteArtists: List<LikedArtist>,
    private val favouriteAlbums: List<LikedAlbum>
) : RecyclerView.Adapter<FavouritesListItemAdapter.FavouritesListItemViewHolder>() {

    companion object {
        const val ARTISTS_HEADER = 0
        const val ALBUMS_HEADER = 1
        const val LIKED_ARTIST = 2
        const val LIKED_ALBUM = 3
    }

    class FavouritesListItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindArtistsHeader() {
            view.findViewById<TextView>(R.id.list_header_title).text = "Artists"
        }

        fun bindAlbumsHeader() {
            view.findViewById<TextView>(R.id.list_header_title).text = "Albums"
        }

        fun bindLikedArtist(data: LikedArtist) {
            view.findViewById<TextView>(R.id.artist_item_title).text = data.name
            if (data.thumbnail != null) {
                val thumbnail = view.findViewById<ImageView>(R.id.artist_item_thumbnail)
                Picasso.get().load(data.thumbnail).into(thumbnail)
            }

            view.setOnClickListener {
                val action = FavouritesFragmentDirections
                    .actionFavouritesFragmentToArtistDetailsFragment(artistId = data.apiId)
                it.findNavController().navigate(action)
            }
        }

        fun bindLikedAlbum(data: LikedAlbum) {
            view.findViewById<TextView>(R.id.album_item_title).text = data.name
            view.findViewById<TextView>(R.id.album_item_artist).text = data.artist
            if (data.thumbnail != null) {
                val thumbnail = view.findViewById<ImageView>(R.id.album_item_thumbnail)
                Picasso.get().load(data.thumbnail).into(thumbnail)
            }

            view.setOnClickListener {
                val action = FavouritesFragmentDirections
                    .actionFavouritesFragmentToAlbumDetailsFragment(albumId = data.apiId)
                it.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavouritesListItemViewHolder {
        val layoutId = when(viewType) {
            LIKED_ARTIST -> R.layout.artist_item
            LIKED_ALBUM -> R.layout.album_item
            ARTISTS_HEADER, ALBUMS_HEADER -> R.layout.list_header
            else -> throw Exception("Invalid view type.")
        }
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(layoutId, parent, false)
        return FavouritesListItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: FavouritesListItemViewHolder, position: Int) {
        when(getItemViewType(position)) {
            ARTISTS_HEADER -> holder.bindArtistsHeader()
            ALBUMS_HEADER -> holder.bindAlbumsHeader()
            LIKED_ARTIST -> holder.bindLikedArtist(favouriteArtists[position - 1])
            LIKED_ALBUM -> holder.bindLikedAlbum(favouriteAlbums[position - favouriteArtists.size - 2])
            else -> throw Exception("Invalid view type.")
        }
    }

    override fun getItemCount(): Int {
        return 2 + favouriteArtists.size + favouriteAlbums.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) ARTISTS_HEADER
            else if (position == favouriteArtists.size + 1) ALBUMS_HEADER
            else if (1 <= position && position <= favouriteArtists.size) LIKED_ARTIST
            else if (favouriteArtists.size + 1 < position && position <= 1 + favouriteArtists.size + favouriteAlbums.size) LIKED_ALBUM
            else throw Exception("Invalid view type.")
    }
}