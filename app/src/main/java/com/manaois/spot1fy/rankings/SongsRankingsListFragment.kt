package com.manaois.spot1fy.rankings

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.manaois.spot1fy.R
import com.manaois.spot1fy.common.APIErrorDialogUtils
import com.manaois.spot1fy.rankings.models.RankedItem
import com.manaois.spot1fy.rankings.network.RankingsApiRequest
import kotlinx.android.synthetic.main.fragment_rankings_list.*
import kotlinx.coroutines.*

class SongsRankingsListFragment: Fragment() {
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

        fillLayout(view)
    }

    private fun fillLayout(view: View) {
        GlobalScope.launch() {
            try {
                withContext(Dispatchers.Main) {
                    loader.visibility = View.VISIBLE
                }

                val result = RankingsApiRequest.getRankedSongs()

                withContext(Dispatchers.Main) {
                    recyclerView = view.findViewById<RecyclerView>(R.id.rankings_list).apply {
                        adapter = RankingsItemAdapter(result)
                    }
                    loader.visibility = View.GONE
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    APIErrorDialogUtils.showErrorDialog(view.context, view, ::fillLayout)
                }
            }
        }
    }
}