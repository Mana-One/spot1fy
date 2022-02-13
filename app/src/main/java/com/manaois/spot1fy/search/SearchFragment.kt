package com.manaois.spot1fy.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.manaois.spot1fy.R
import com.manaois.spot1fy.search.network.SearchApiRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchFragment: Fragment() {
    private lateinit var loader: View
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editText: EditText = view.findViewById(R.id.search_input)
        loader= view.findViewById(R.id.loader)
        recyclerView = view.findViewById<RecyclerView>(R.id.search_list)

        editText.addTextChangedListener(
            afterTextChanged = {
                println("Text CHANGED")
                val input = it?.toString()
                if (input != null && input.isNotEmpty()) {
                    search(input)
                }
            }
        )


        view.findViewById<View>(R.id.search_cancel_icon).setOnClickListener {
            editText.setText("")
        }

        view.findViewById<RecyclerView>(R.id.search_list).apply {
            adapter = SearchListItemAdapter(listOf(), listOf())
        }
    }

    private fun search(input: String) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                println("Show loader")
                loader.visibility = View.VISIBLE
            }

            val albums = SearchApiRequest.searchAlbums(input) ?: listOf()
            val artists = SearchApiRequest.searchArtists(input) ?: listOf()

            withContext(Dispatchers.Main) {
                recyclerView.apply {
                    adapter = SearchListItemAdapter(artists, albums)
                }
                println("hide loader")
                loader.visibility = View.GONE
            }
        }
    }
}