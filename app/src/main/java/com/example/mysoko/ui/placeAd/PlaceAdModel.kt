package com.example.mysoko.ui.placeAd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlaceAdModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is placeAd Fragment"
    }
    val text: LiveData<String> = _text
}