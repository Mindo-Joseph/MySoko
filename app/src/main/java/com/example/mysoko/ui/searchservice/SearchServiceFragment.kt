package com.example.mysoko.ui.searchservice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mysoko.R

class SearchServiceFragment : Fragment() {

    private lateinit var searchServiceModel: SearchServiceModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchServiceModel =
            ViewModelProviders.of(this).get(SearchServiceModel::class.java)
        val root = inflater.inflate(R.layout.fragment_search_service, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        searchServiceModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}