package com.example.pickingbomb.ui.view.search.presentaion

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pickingbomb.network.Resource
import com.example.pickingbomb.ui.view.search.model.ImageSearchItems
import kotlinx.coroutines.launch
import okhttp3.MultipartBody



class ImageSearchViewModel(private val repository: ImageSearchRepository) : ViewModel() {

    private val _imageSearchState :MutableLiveData<Resource<ImageSearchItems.ImageSearchResponse>> = MutableLiveData()
    val imageSearchState: LiveData<Resource<ImageSearchItems.ImageSearchResponse>> = _imageSearchState

    fun searchImage(
        imagePart: MultipartBody.Part
    )= viewModelScope.launch {
        _imageSearchState.value = Resource.Loading
        _imageSearchState.value = repository.searchImage(imagePart)
        Log.d("navigate", _imageSearchState.value.toString())

    }

}

