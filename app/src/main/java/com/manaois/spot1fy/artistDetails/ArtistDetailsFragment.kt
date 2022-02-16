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
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_artist_details.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class ArtistDetailsFragment : Fragment() {
    private lateinit var artistId: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        artistId = arguments?.get("artistId").toString()
        return inflater.inflate(R.layout.fragment_artist_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.artist_details_back_arrow).setOnClickListener {
            requireActivity().findNavController(R.id.nav_host_fragment).navigateUp()
        }

        loadContent()
    }

    private fun loadContent() {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                loader.visibility = View.VISIBLE
            }

            val artistDetails = ArtistDetailsApiRequest.getArtistDetails(artistId)
            val albums = ArtistDetailsApiRequest.getArtistAlums(artistId).sortedByDescending { it.year }
            val popularSongs = ArtistDetailsApiRequest.getArtistPopularSongs(artistDetails.name)

            withContext(Dispatchers.Main) {
                Picasso.get().load(artistDetails.image).into(artist_details_image)
                artist_details_name.text = artistDetails.name
                artist_details_locality_and_genre.text = "${artistDetails.locality} - ${artistDetails.genre}"
                artist_details_biography.text = artistDetails.getBiography(Locale.getDefault().displayLanguage)
                artist_details_discography.apply {
                    adapter = ArtistDiscographyItemAdapter(albums, popularSongs)
                }

                loader.visibility = View.GONE
            }
        }
    }
}