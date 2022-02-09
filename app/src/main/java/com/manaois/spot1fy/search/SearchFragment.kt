package com.manaois.spot1fy.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.manaois.spot1fy.R

class SearchFragment: Fragment() {
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
        view.findViewById<View>(R.id.search_cancel_icon).setOnClickListener {
            editText.setText("")
        }

        view.findViewById<RecyclerView>(R.id.search_list).apply {
            adapter = SearchListItemAdapter(requireContext())
        }

    }
}