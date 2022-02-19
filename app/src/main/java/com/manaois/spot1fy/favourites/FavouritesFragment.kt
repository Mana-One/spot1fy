package com.manaois.spot1fy.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.manaois.spot1fy.R
import com.manaois.spot1fy.database.DatabaseManager
import kotlinx.android.synthetic.main.fragment_favourites.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

        loadData()
    }

    private fun loadData() {
        GlobalScope.launch {
            val dbManager = DatabaseManager(requireContext())
            val likedArtists = dbManager.getLikedArtist()
            val likedAlbums = dbManager.getLikedAlbums()

            withContext(Dispatchers.Main) {
                favourites_list.apply {
                    adapter = FavouritesListItemAdapter(likedArtists, likedAlbums)
                }
            }
        }
    }
}