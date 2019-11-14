package com.example.mysoko.ui.searchservice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchServiceModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is searchservice Fragment"
    }
    val text: LiveData<String> = _text
}