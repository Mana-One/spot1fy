package com.manaois.spot1fy.rankings

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.manaois.spot1fy.R
import com.manaois.spot1fy.rankings.models.RankedItem
import com.squareup.picasso.Picasso

class RankingsItemAdapter(
    private val context: Context,
    private val dataset: List<RankedItem>
) : RecyclerView.Adapter<RankingsItemAdapter.RankingsItemViewHolder>() {

    class RankingsItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val rankTextView = view.findViewById<TextView>(R.id.rankings_item_rank)
        val thumbnailImageView = view.findViewById<ImageView>(R.id.rankings_item_thumbnail)
        val titleTextView = view.findViewById<TextView>(R.id.rankings_item_title)
        val artistTextView = view.findViewById<TextView>(R.id.rankings_item_artist)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingsItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.rankings_item, parent, false)
        return RankingsItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: RankingsItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.rankTextView.text = "$position"
        Picasso.get().load(item.thumbnail).into(holder.thumbnailImageView)
        holder.titleTextView.text = item.name
        holder.artistTextView.text = item.artist
    }

    override fun getItemCount() = dataset.size
}