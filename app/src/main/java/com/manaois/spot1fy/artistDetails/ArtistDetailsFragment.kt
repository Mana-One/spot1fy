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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArtistDetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_artist_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.artist_details_back_arrow).setOnClickListener {
            requireActivity().findNavController(R.id.nav_host_fragment).navigateUp()
        }

        loadContent(view)
    }

    private fun loadContent(view: View) {
        val loader = view.findViewById<View>(R.id.loader)
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                loader.visibility = View.VISIBLE
            }

            val artistDetails = ArtistDetailsApiRequest.getArtistDetails("147737")
            val albums = ArtistDetailsApiRequest.getArtistAlums("147737")
            val popularSongs = ArtistDetailsApiRequest.getArtistPopularSongs(artistDetails.name)

            println("____")
            println("2 ${albums.size} ${popularSongs.size}")
            println("____")

            withContext(Dispatchers.Main) {
                val image: ImageView = view.findViewById(R.id.artist_details_image)
                val artistName: TextView = view.findViewById(R.id.artist_details_name)
                val localityAndGenre: TextView = view.findViewById(R.id.artist_details_locality_and_genre)
                val biography: TextView = view.findViewById(R.id.artist_details_biography)
                val discography: RecyclerView = view.findViewById(R.id.artist_details_discography)

                Picasso.get().load(artistDetails.image).into(image)
                artistName.text = artistDetails.name
                localityAndGenre.text = "${artistDetails.locality} - ${artistDetails.genre}"
                biography.text = artistDetails.biography
                discography.apply {
                    adapter = ArtistDiscographyItemAdapter(albums, popularSongs)
                }

                loader.visibility = View.GONE
            }
        }
    }
}