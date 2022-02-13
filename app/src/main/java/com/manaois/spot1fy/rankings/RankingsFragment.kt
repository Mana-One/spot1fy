package com.manaois.spot1fy.rankings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.manaois.spot1fy.R
import com.manaois.spot1fy.databinding.FragmentRankingsBinding

class RankingsFragment: Fragment() {
    private lateinit var rankingsAdapter: RankingsAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rankings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rankingsAdapter = RankingsAdapter(this)
        viewPager = view.findViewById(R.id.rankings_pager)
        viewPager.adapter = rankingsAdapter
        val tabLayout: TabLayout = view.findViewById(R.id.rankings_tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = if (position == 0) "Songs" else "Albums"
        }.attach()
    }
}