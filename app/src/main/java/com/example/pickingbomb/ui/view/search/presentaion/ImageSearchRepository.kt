package com.example.pickingbomb.ui.view.search.presentaion

import com.example.pickingbomb.network.SafeApiCall
import com.example.pickingbomb.ui.view.search.model.ImageSearchItems
import okhttp3.MultipartBody
import retrofit2.Response

class ImageSearchRepository(private val apiService: ImageSearchApiService):SafeApiCall {

    suspend fun searchImage(imagePart: MultipartBody.Part)=safeApiCall {
        apiService.searchImage(imagePart)
    }
}
