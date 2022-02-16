package com.manaois.spot1fy.rankings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.manaois.spot1fy.R
import com.manaois.spot1fy.rankings.network.RankingsApiRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlbumsRankingsListFragment: Fragment() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rankings_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loader = view.findViewById<View>(R.id.loader)
        GlobalScope.launch() {
            withContext(Dispatchers.Main) {
                loader.visibility = View.VISIBLE
            }

            val result = RankingsApiRequest.getRankedAlbums()

            withContext(Dispatchers.Main) {
                recyclerView = view.findViewById<RecyclerView>(R.id.rankings_list).apply {
                    adapter = RankingsItemAdapter(result)
                }
                loader.visibility = View.GONE
            }
        }
    }
}