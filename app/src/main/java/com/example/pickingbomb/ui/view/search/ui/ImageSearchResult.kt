package com.example.pickingbomb.ui.view.search.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.pickingbomb.ui.theme.FontFamily
import com.example.pickingbomb.ui.theme.TextColor
import com.example.pickingbomb.ui.view.search.holder.SharedViewModel


@Composable
fun ImageSearchResult(navController: NavController) {

    val sharedViewModel: SharedViewModel =
        viewModel(LocalContext.current as androidx.activity.ComponentActivity)
    val imageSearchData by sharedViewModel.imageSearchData.observeAsState()

    BackHandler {
        sharedViewModel.setImageSearchData(null)
    }
        Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 0.dp, 16.dp, 0.dp),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        imageSearchData?.success?.let { successList ->
                LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
            ) {
                items(successList.size) {index ->
                    val successItem = successList[index]
                    Box {
                        successItem.image_url?.let { imageUrl ->
                            val painter = rememberAsyncImagePainter(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(imageUrl)
                                    .crossfade(true)
                                    .build()
                            )

                            Card(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White
                                ),
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalAlignment = CenterHorizontally
                                ){
                                    painter?.let {
                                        Image(
                                            painter = it,
                                            contentDescription = "Images",
                                            contentScale = ContentScale.FillBounds,
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .clip(RoundedCornerShape(12.dp))
                                                .aspectRatio(1f)
                                        )

                                    }
                                }

                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(8.dp, 12.dp, 6.dp, 12.dp),
                                    horizontalAlignment = Alignment.Start
                                ){
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Sku :${successItem.sku ?: ""}",
                                        fontSize = 10.sp,
                                        fontFamily = FontFamily,
                                        color = TextColor
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Similarity :${
                                            successItem.similarity_percentage?.substringBefore(
                                                "."
                                            ) ?: ""
                                        }%",
                                        fontSize = 10.sp,
                                        fontFamily = FontFamily,
                                        color = TextColor
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Color :${successItem.color ?: ""}",
                                        fontSize = 10.sp,
                                        fontFamily = FontFamily,
                                        color = TextColor
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Fabric :${successItem.fabric ?: ""}",
                                        fontSize = 10.sp,
                                        fontFamily = FontFamily,
                                        color = TextColor
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Coverage :${successItem.coverage ?: ""}",
                                        fontSize = 10.sp,
                                        fontFamily = FontFamily,
                                        color = TextColor
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Color Family :${successItem.color_family ?: ""}",
                                        fontSize = 10.sp,
                                        fontFamily = FontFamily,
                                        color = TextColor
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

//@Composable
//private fun CardData(imageSearchData: ImageSearchItems.Success) {
//
//    Card(
//        modifier = Modifier.fillMaxSize(),
//        RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = Color.White)
//    ) {
//        val paintImage =imageSearchData.image_url
//        Image(
//            painter = painterResource(id = paintImage!!),
//            contentDescription = "image", contentScale = ContentScale.Crop,
//            modifier = Modifier.fillMaxWidth()
//        )
//        Text(
//            text = "Sku : ${imageSearchData.sku}",
//            modifier = Modifier
//                .padding(start = 8.dp, top = 8.dp, end = 16.dp),
//            fontSize = 14.sp,
//            color = TextColor,
//            fontFamily = FontFamily.SansSerif
//        )
//
//        Text(
//            text = "Similarity : ${imageSearchData.similarity_percentage}", modifier = Modifier
//                .padding(start = 8.dp, top = 8.dp, end = 16.dp)
//        )
//
//        Text(
//            text = "Color : ${imageSearchData.color}", modifier = Modifier
//                .padding(start = 8.dp, top = 8.dp, end = 16.dp)
//        )
//
//        Text(
//            text = "Fabric :${imageSearchData.fabric}", modifier = Modifier
//                .padding(start = 8.dp, top = 8.dp, end = 16.dp)
//        )
//
//        Text(
//            text = "Coverage : ${imageSearchData.coverage}", modifier = Modifier
//                .padding(start = 8.dp, top = 8.dp, end = 16.dp)
//        )
//
//        Text(
//            text = "Color Family : ${imageSearchData.color_family}", modifier = Modifier
//                .padding(start = 8.dp, top = 8.dp, end = 16.dp, bottom = 16.dp)
//        )
//
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun ImageSearchResultPreview() {
//    ImageSearchResult()
//}







