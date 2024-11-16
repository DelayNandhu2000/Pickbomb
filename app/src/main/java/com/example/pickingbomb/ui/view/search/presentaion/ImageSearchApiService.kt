package com.example.pickingbomb.ui.view.search.presentaion

import com.example.pickingbomb.network.Resource
import com.example.pickingbomb.ui.view.search.model.ImageSearchItems
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface ImageSearchApiService {
    @Multipart
    @POST("get_similar_image")
    suspend fun searchImage(
        @Part file: MultipartBody.Part
    ): ImageSearchItems.ImageSearchResponse
}