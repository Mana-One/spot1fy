package com.manaois.spot1fy.rankings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.manaois.spot1fy.R
import com.manaois.spot1fy.rankings.models.RankedAlbum
import com.manaois.spot1fy.rankings.models.RankedItem
import com.squareup.picasso.Picasso

class RankingsItemAdapter(
    private val dataset: List<RankedItem>
) : RecyclerView.Adapter<RankingsItemAdapter.RankingsItemViewHolder>() {

    class RankingsItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(rankedItem: RankedItem, position: Int) {
            val rankTextView = view.findViewById<TextView>(R.id.rankings_item_rank)
            val thumbnailImageView = view.findViewById<ImageView>(R.id.rankings_item_thumbnail)
            val titleTextView = view.findViewById<TextView>(R.id.rankings_item_title)
            val artistTextView = view.findViewById<TextView>(R.id.rankings_item_artist)

            rankTextView.text = "${position + 1}"
            thumbnailImageView.clipToOutline = true
            Picasso.get().load(rankedItem.thumbnail).into(thumbnailImageView)
            titleTextView.text = rankedItem.name
            artistTextView.text = rankedItem.artist

            if (rankedItem is RankedAlbum) {
                view.setOnClickListener {
                    val action = RankingsFragmentDirections
                        .actionRankingsFragmentToAlbumDetailsFragment(albumId = rankedItem.id)
                    it.findNavController().navigate(action)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingsItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.rankings_item, parent, false)
        return RankingsItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: RankingsItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.bind(item, position)
    }

    override fun getItemCount() = dataset.size
}