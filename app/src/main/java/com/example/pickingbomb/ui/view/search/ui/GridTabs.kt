package com.example.pickingbomb.ui.view.search.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pickingbomb.R
import com.example.pickingbomb.ui.view.search.model.ImageSearchItems

@Composable
fun GridTabs(){
   val gridTabItems = listOf(
       ImageSearchItems.TestUse(image_url = R.drawable.red_banner,sku = "Banner"),
       ImageSearchItems.TestUse(image_url = R.drawable.blue_banner,sku = "Banner"),
       ImageSearchItems.TestUse(image_url = R.drawable.green_banner,sku = "Banner"),
       ImageSearchItems.TestUse(image_url = R.drawable.yellow_banner,sku = "Banner"),
   )
    LazyColumn{
        items(gridTabItems){
            GridBanner(it)
        }
    }
}

@Composable
fun GridBanner(items: ImageSearchItems.TestUse){
    ConstraintLayout(modifier = Modifier.fillMaxWidth()){
        val(image, title) = createRefs()
        Surface(modifier = Modifier
            .clickable {  }
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(shape = RoundedCornerShape(18.dp))
            .padding(2.dp))
        {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(items.image_url)
                    .build(),
                modifier = Modifier.fillMaxWidth(),
                contentDescription = "gridBanner",
            )
            Text(
                text = items.sku.toString(),
                modifier = Modifier
                    .wrapContentWidth(align = Alignment.Start)
                    .constrainAs(title) {
                        top.linkTo(image.top)
                        start.linkTo(image.start)
                    }
                    .padding(10.dp),
                color = Color.White,
                fontSize = 20.sp
            )

        }
    }
}

@Preview
@Composable
fun GridTabsPreview(){
    GridTabs()
}