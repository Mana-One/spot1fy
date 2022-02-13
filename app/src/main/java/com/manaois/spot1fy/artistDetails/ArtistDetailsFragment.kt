package com.manaois.spot1fy.artistDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.manaois.spot1fy.R
import com.squareup.picasso.Picasso

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

        val artistImage: ImageView = view.findViewById(R.id.artist_details_image)
        Picasso.get()
            .load("https://www.theaudiodb.com/images/media/artist/widethumb/wvxqss1520001535.jpg")
            .into(artistImage)

        view.findViewById<RecyclerView>(R.id.artist_details_discography).apply {
            adapter = ArtistDiscographyItemAdapter()
        }
    }
}