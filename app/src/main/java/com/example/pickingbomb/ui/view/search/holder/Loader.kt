package com.example.pickingbomb.ui.view.search.holder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.pickingbomb.R


@Composable
fun Loader(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box{
            AsyncImage(model = R.drawable.empty_cart, contentDescription = "app_gif")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun LoaderPreview(){
    Loader()
}

