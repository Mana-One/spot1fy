package com.manaois.spot1fy.artistDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.manaois.spot1fy.R
import com.manaois.spot1fy.artistDetails.network.ArtistDetailsApiRequest
import com.manaois.spot1fy.database.DatabaseManager
import com.manaois.spot1fy.database.LikedArtist
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_artist_details.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class ArtistDetailsFragment : Fragment() {
    private lateinit var artistId: String
    private var isLiked: Boolean = false
    private lateinit var likedArtist: LikedArtist
    private lateinit var dbManager: DatabaseManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        artistId = arguments?.get("artistId").toString()
        dbManager = DatabaseManager(requireContext())
        return inflater.inflate(R.layout.fragment_artist_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        artist_details_back_arrow.setOnClickListener {
            requireActivity().findNavController(R.id.nav_host_fragment).navigateUp()
        }

        loadContent()

        artist_details_like_icon.setOnClickListener {
            if (isLiked) unlike()
            else like()
        }
    }

    private fun loadContent() {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                loader.visibility = View.VISIBLE
            }

            val artistDetails = ArtistDetailsApiRequest.getArtistDetails(artistId)
            val albums = ArtistDetailsApiRequest.getArtistAlums(artistId).sortedByDescending { it.year }
            val popularSongs = ArtistDetailsApiRequest.getArtistPopularSongs(artistDetails.name)

            val check = dbManager.findLikedArtist(artistId)
            if (check != null){
                isLiked = true
                likedArtist = check
            } else {
                isLiked = false
                likedArtist = LikedArtist(apiId = artistId, name = artistDetails.name, thumbnail = artistDetails.image)
            }


            withContext(Dispatchers.Main) {
                Picasso.get().load(artistDetails.image).into(artist_details_image)
                artist_details_name.text = artistDetails.name
                artist_details_locality_and_genre.text = "${artistDetails.locality} - ${artistDetails.genre}"
                artist_details_biography.text = artistDetails.getBiography(Locale.getDefault().displayLanguage)
                artist_details_discography.apply {
                    adapter = ArtistDiscographyItemAdapter(albums, popularSongs)
                }

                val resBackground = if (isLiked) R.drawable.ic_like_on
                    else R.drawable.ic_like_off
                artist_details_like_icon.setBackgroundResource(resBackground)

                loader.visibility = View.GONE
            }
        }
    }

    private fun like() {
        GlobalScope.launch {
            dbManager.addLikedArtist(likedArtist)
            isLiked = true
            withContext(Dispatchers.Main) {
                artist_details_like_icon.setBackgroundResource(R.drawable.ic_like_on)
            }
        }
    }

    private fun unlike() {
        GlobalScope.launch {
            dbManager.removeLikedArtist(likedArtist)
            isLiked = false
            withContext(Dispatchers.Main) {
                artist_details_like_icon.setBackgroundResource(R.drawable.ic_like_off)
            }
        }
    }
}