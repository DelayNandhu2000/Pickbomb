package com.example.pickingbomb.ui.view.search.model

sealed class ImageSearchItems {

    data class ImageSearchResponse(
        val image_attributes: ImageAttributes? = null,
        val total_matches: Int? = null,
        val path: String? = null,
        val success:List<Success>? = null,
    ):ImageSearchItems()

    data class ImageAttributes(
        val category: String? = null,
        val color:String?= null,
        val color_family:String?= null,
        val fabric:String?= null,
        val coverage:String?= null,
        val shy_color:String?= null,
    ):ImageSearchItems()

    data class Success(
        val image_url:String?= null,
        val sku:String?= null,
        val similarity_percentage:String?= null,
        val color:String?= null,
        val color_family:String?= null,
        val fabric:String?= null,
        val coverage:String?= null
    ):ImageSearchItems()


//...................................................................................

    data class TestUse(
        val image_url:Int?= null,
        val sku:String?= null,
        val similarity_percentage:String?= null,
        val color:String?= null,
        val color_family:String?= null,
        val fabric:String?= null,
        val coverage:String?= null
    ):ImageSearchItems()
}