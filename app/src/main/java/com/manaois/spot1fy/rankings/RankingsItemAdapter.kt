package com.manaois.spot1fy.rankings

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.manaois.spot1fy.R

class RankingsItemAdapter(
    private val context: Context,
    private val dataset: List<String> = List(10) { "Hey I am an element !" }
) : RecyclerView.Adapter<RankingsItemAdapter.RankingsItemViewHolder>() {

    class RankingsItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.rankings_item_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingsItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.rankings_item, parent, false)
        return RankingsItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: RankingsItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = "$position - $item"
    }

    override fun getItemCount() = dataset.size
}