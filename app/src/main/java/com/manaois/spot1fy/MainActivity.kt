package com.manaois.spot1fy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.manaois.spot1fy.rankings.RankingsItemAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rankings_list)
        recyclerView.adapter = RankingsItemAdapter(this)
        recyclerView.setHasFixedSize(true)
    }
}