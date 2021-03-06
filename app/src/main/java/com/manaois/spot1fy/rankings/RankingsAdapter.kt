package com.manaois.spot1fy.rankings

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.lang.IllegalStateException

class RankingsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> SongsRankingsListFragment()
            1 -> AlbumsRankingsListFragment()
            else -> throw IllegalStateException("Non-existent tab")
        }
    }
}