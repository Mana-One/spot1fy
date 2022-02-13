package com.manaois.spot1fy.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.manaois.spot1fy.R
import com.manaois.spot1fy.favourites.models.LikedAlbum
import com.manaois.spot1fy.favourites.models.LikedArtist

class FavouritesFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favouriteArtists = List(5) { LikedArtist("Artist name", null) }
        val favouriteAlbums = List(5) { LikedAlbum("Album name", "Artist name", null) }
        view.findViewById<RecyclerView>(R.id.favourites_list).apply {
            adapter = FavouritesListItemAdapter(favouriteArtists, favouriteAlbums)
        }
    }
}