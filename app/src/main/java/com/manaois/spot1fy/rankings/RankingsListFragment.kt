package com.manaois.spot1fy.rankings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.manaois.spot1fy.R
import com.manaois.spot1fy.rankings.models.RankedItem
import com.manaois.spot1fy.rankings.network.RankingsApiRequest
import kotlinx.coroutines.*

class RankingsListFragment(private val label: String): Fragment() {
    private lateinit var adapter: RankingsItemAdapter

    companion object {
        const val TYPE_SONG = "song"
        const val TYPE_ALBUM = "album"
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
        val recyclerView = view.findViewById<RecyclerView>(R.id.rankings_list)
        val adapter = RankingsItemAdapter(requireContext(), listOf())
        recyclerView.adapter = adapter

        GlobalScope.launch(Dispatchers.Main) {
            val result = if (label == TYPE_SONG) {
                RankingsApiRequest.getRankedSongs()
            } else {
                RankingsApiRequest.getRankedAlbums()
            }
            val adapter = RankingsItemAdapter(requireContext(), result)
            recyclerView.swapAdapter(adapter, true)
        }
    }
}