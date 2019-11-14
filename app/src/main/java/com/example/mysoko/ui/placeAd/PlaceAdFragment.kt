package com.example.mysoko.ui.placeAd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mysoko.R

class PlaceAdFragment : Fragment() {

    private lateinit var placeAdModel: PlaceAdModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        placeAdModel =
            ViewModelProviders.of(this).get(PlaceAdModel::class.java)
        val root = inflater.inflate(R.layout.fragment_place_ad, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        placeAdModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}