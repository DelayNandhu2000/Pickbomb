package com.example.pickingbomb.network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pickingbomb.ui.view.search.presentaion.ImageSearchRepository
import com.example.pickingbomb.ui.view.search.presentaion.ImageSearchViewModel

class ImageSearchViewModelFactory(private val repository: ImageSearchRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImageSearchViewModel::class.java)) {
            return ImageSearchViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
