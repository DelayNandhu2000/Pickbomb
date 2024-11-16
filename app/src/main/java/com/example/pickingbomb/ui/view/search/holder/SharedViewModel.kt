package com.example.pickingbomb.ui.view.search.holder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pickingbomb.ui.view.search.model.ImageSearchItems

class SharedViewModel():ViewModel() {

    private val _imageSearchData = MutableLiveData<ImageSearchItems.ImageSearchResponse?>()
    val imageSearchData: LiveData<ImageSearchItems.ImageSearchResponse?> = _imageSearchData

    fun setImageSearchData(data: ImageSearchItems.ImageSearchResponse? ) {
        _imageSearchData.value = data
    }

}