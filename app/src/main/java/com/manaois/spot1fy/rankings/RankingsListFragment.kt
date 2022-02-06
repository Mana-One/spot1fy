package com.manaois.spot1fy.rankings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.manaois.spot1fy.R
import com.manaois.spot1fy.rankings.models.RankedItem

class RankingsListFragment(private val label: String): Fragment() {
    private lateinit var dataset: List<RankedItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataset = if (label == "song") {
            RankedItem.mockSongs()
        } else RankedItem.mockAlbums()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rankings_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.rankings_list)
        val adapter = RankingsItemAdapter(requireContext(), dataset)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
    }
}