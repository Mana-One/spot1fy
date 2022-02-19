package com.manaois.spot1fy.albumDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.manaois.spot1fy.R
import com.manaois.spot1fy.albumDetails.models.AlbumDetails
import com.manaois.spot1fy.albumDetails.models.AlbumSong
import com.manaois.spot1fy.albumDetails.network.AlbumDetailsRequest
import com.manaois.spot1fy.database.DatabaseManager
import com.manaois.spot1fy.database.LikedAlbum
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_album_details.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.loader
import kotlinx.android.synthetic.main.ratings_container.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class AlbumDetailsFragment: Fragment() {
    private lateinit var albumId: String
    private var isLiked: Boolean = false
    private lateinit var likedAlbum: LikedAlbum
    private lateinit var dbManager: DatabaseManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        albumId = arguments?.get("albumId").toString()
        dbManager = DatabaseManager(requireContext())
        return inflater.inflate(R.layout.fragment_album_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        album_details_back_arrow.setOnClickListener {
            requireActivity().findNavController(R.id.nav_host_fragment).navigateUp()
        }

        loadContent()

        album_details_like_icon.setOnClickListener {
            if (isLiked) unlike()
            else like()
        }
    }

    private fun loadContent() {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                loader.visibility = View.VISIBLE
            }

            val albumDetails = AlbumDetailsRequest.getAlbumDetails(albumId)
            val albumSongs = AlbumDetailsRequest.getAlbumSongs(albumId)

            checkLike(albumDetails)

            withContext(Dispatchers.Main) {
                fillLayout(albumDetails, albumSongs)
            }
        }
    }

    private fun checkLike(albumDetails: AlbumDetails) {
        val check = dbManager.findLikedAlbum(albumId)
        if (check != null) {
            isLiked = true
            likedAlbum = check
        } else {
            isLiked = false
            likedAlbum = LikedAlbum(
                apiId = albumId,
                name = albumDetails.name,
                artist = albumDetails.artist,
                thumbnail = albumDetails.thumbnail
            )
        }
    }

    private fun fillLayout(albumDetails: AlbumDetails, albumSongs: List<AlbumSong>) {
        if (albumDetails.thumbnail != null) {
            Picasso.get().load(albumDetails.thumbnail).into(album_details_image)
            Picasso.get().load(albumDetails.thumbnail).into(album_details_thumbnail)
        }
        album_details_artist_name.text = albumDetails.artist
        album_details_name.text = albumDetails.name
        album_details_songs_count.text = "${albumSongs.size} songs"
        ratings_score.text = "${albumDetails.score}"
        ratings_votes.text = "${albumDetails.votes} votes"
        album_details_description.text = albumDetails
            .getDescription(Locale.getDefault().displayLanguage)
        album_details_songs.apply {
            adapter = AlbumSongsItemAdapter(albumSongs)
        }

        val resDrawable = if (isLiked) R.drawable.ic_like_on
        else R.drawable.ic_like_off
        album_details_like_icon.setBackgroundResource(resDrawable)

        loader.visibility = View.GONE
    }

    private fun like() {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                loader.visibility = View.VISIBLE
            }

            dbManager.addLikedAlbum(likedAlbum)
            isLiked = true

            withContext(Dispatchers.Main) {
                album_details_like_icon.setBackgroundResource(R.drawable.ic_like_on)
                loader.visibility = View.GONE
            }
        }
    }

    private fun unlike() {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                loader.visibility = View.VISIBLE
            }

            dbManager.removeLikedAlbum(likedAlbum)
            isLiked = false

            withContext(Dispatchers.Main) {
                album_details_like_icon.setBackgroundResource(R.drawable.ic_like_off)
                loader.visibility = View.GONE
            }
        }
    }
}