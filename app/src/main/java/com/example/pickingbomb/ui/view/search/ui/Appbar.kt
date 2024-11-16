package com.example.pickingbomb.ui.view.search.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.AirportShuttle
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Shop
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.pickingbomb.ui.theme.BrandColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(){

    val context = LocalContext.current
    var selectedIndex by remember { mutableIntStateOf(0) }
    val navigationItems = remember { NavigationItems.entries }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = BrandColor
                ),
                title = {
                    Text(text = "Banner Carousel")
                },
                actions = {
                    IconButton(onClick = { Toast.makeText(context, "itemClicked", Toast.LENGTH_SHORT).show()}) {
                        Icon(imageVector = Icons.Outlined.ShoppingBag, contentDescription = "AccountBalance", tint = BrandColor)
                    }
                    IconButton(onClick = { Toast.makeText(context, "itemClicked", Toast.LENGTH_SHORT).show()}) {
                        Icon(imageVector = Icons.Outlined.Search, contentDescription = "AccountBalance", tint = BrandColor)
                    }
                    IconButton(onClick = { Toast.makeText(context, "itemClicked", Toast.LENGTH_SHORT).show()}) {
                        Icon(imageVector = Icons.Outlined.Settings, contentDescription = "AccountBalance", tint = BrandColor)
                    }
                },
               navigationIcon = {
                   Icon(Icons.Filled.ArrowBackIosNew, "ArrowBack" , tint = BrandColor)
               }
            )
        },

        bottomBar = {
//            AnimatedNavigationBar(
//                selectedIndex = selectedIndex,
//                cornerRadius = shapeCornerRadius(2f),
//                barColor = BrandColor
//            ) {
//                navigationItems.forEach { item ->
//                    Box(
//                        modifier = Modifier.fillMaxWidth().noRippleClickable {selectedIndex = item.ordinal},
//                    ){
//                        IconButton(onClick = { Toast.makeText(context, "itemClicked", Toast.LENGTH_SHORT).show()}) {
//                        Icon(imageVector = Icons.Filled.AccountBalanceWallet, contentDescription = "AccountBalance", tint = if(selectedIndex == item.ordinal) Color.White else Color.White.copy(alpha = 4f))
//                    }
//                    IconButton(onClick = { Toast.makeText(context, "itemClicked", Toast.LENGTH_SHORT).show()}) {
//                        Icon(imageVector = Icons.Filled.AddPhotoAlternate, contentDescription = "photo",tint = if(selectedIndex == item.ordinal) Color.White else Color.White.copy(alpha = 4f))
//                    }
//                    IconButton(onClick = { Toast.makeText(context, "itemClicked", Toast.LENGTH_SHORT).show()}) {
//                        Icon(imageVector = Icons.Filled.AirportShuttle, contentDescription = "shuttle",tint = if(selectedIndex == item.ordinal) Color.White else Color.White.copy(alpha = 4f))
//                    }
//                    IconButton(onClick = { Toast.makeText(context, "itemClicked", Toast.LENGTH_SHORT).show()}) {
//                        Icon(imageVector = Icons.Filled.Shop, contentDescription = "photo",tint = if(selectedIndex == item.ordinal) Color.White else Color.White.copy(alpha = 4f))
//                    }
//                    }
//                }
//
//            }

            BottomAppBar(
                modifier = Modifier.height(78.dp).clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
                containerColor = BrandColor,
                contentColor = Color.White,
                content = {
                    Row(
                        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ){
                        IconButton(onClick = { Toast.makeText(context, "itemClicked", Toast.LENGTH_SHORT).show()}) {
                            Icon(imageVector = Icons.Filled.AccountBalanceWallet, contentDescription = "AccountBalance")
                        }
                        IconButton(onClick = { Toast.makeText(context, "itemClicked", Toast.LENGTH_SHORT).show()}) {
                            Icon(imageVector = Icons.Filled.AddPhotoAlternate, contentDescription = "photo")
                        }
                        IconButton(onClick = { Toast.makeText(context, "itemClicked", Toast.LENGTH_SHORT).show()}) {
                            Icon(imageVector = Icons.Filled.AirportShuttle, contentDescription = "shuttle")
                        }
                        IconButton(onClick = { Toast.makeText(context, "itemClicked", Toast.LENGTH_SHORT).show()}) {
                            Icon(imageVector = Icons.Filled.Shop, contentDescription = "photo")
                        }
                    }

                },
                contentPadding = BottomAppBarDefaults.ContentPadding,
                tonalElevation = BottomAppBarDefaults.ContainerElevation,
            )
        }
    ){innerPaddingValues ->
        PagerScreen(innerPaddingValues)
    }


}

enum class NavigationItems {
    Home,
    Profile,
    Image,
    Settings
}