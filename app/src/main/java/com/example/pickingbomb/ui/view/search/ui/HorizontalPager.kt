package com.example.pickingbomb.ui.view.search.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Man
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AcUnit
import androidx.compose.material.icons.outlined.AccessAlarm
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Man
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pickingbomb.R
import com.example.pickingbomb.ui.theme.BrandColor
import com.example.pickingbomb.ui.view.search.model.ImageSearchItems
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PagerScreen(paddingValues: PaddingValues) {

    val autoPager = listOf(
        ImageSearchItems.TestUse(image_url = R.drawable.red_banner),
        ImageSearchItems.TestUse(image_url = R.drawable.blue_banner),
        ImageSearchItems.TestUse(image_url = R.drawable.green_banner),
        ImageSearchItems.TestUse(image_url = R.drawable.yellow_banner),
    )

//-------------------------------------------------------------------------------------------------

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { TabRowPage.entries.size })
    val pagerStates = rememberPagerState(pageCount = { autoPager.size })
    val selectedIndex = remember { derivedStateOf { pagerState.currentPage } }

    LaunchedEffect(key1 = pagerStates.currentPage){
        while (true) {
            delay(3000L)
            val nextPage = (pagerStates.currentPage + 1) % pagerStates.pageCount
            scope.launch {
                pagerStates.scrollToPage(nextPage)
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        HorizontalPager(
            state = pagerStates,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(alignment = Alignment.CenterHorizontally)
        ) { page ->
            val imageUrl = remember(page) { autoPager[page].image_url }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ){
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .build(),
                    contentDescription = "banner",
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
                Row(
                    Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 4.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(pagerStates.pageCount) { iteration ->
                        val color =
                            if (pagerStates.currentPage == iteration) Color.White else Color.LightGray
                        Box(
                            modifier = Modifier
                                .padding(2.dp)
                                .clip(CircleShape)
                                .background(color)
                                .size(6.dp)
                        )
                    }
                }

            }
        }

            TabRow(
                selectedTabIndex = selectedIndex.value,
                modifier = Modifier.fillMaxWidth(),
                containerColor = BrandColor,
                indicator = {
                    SecondaryIndicator(
                        Modifier.tabIndicatorOffset(it[selectedIndex.value]),
                        color = Color.Transparent
                    )
                }
            ) {
                TabRowPage.entries.forEachIndexed { index, tabRowPage ->
                    Tab(
                        selected = selectedIndex.value == index,
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.White.copy(0.4f),
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(tabRowPage.ordinal)
                            }
                        },
                        text = { Text(text = tabRowPage.title) },
                        icon = {
                            Icon(
                                imageVector = if (selectedIndex.value == index) {
                                    tabRowPage.selectedIcon
                                } else {
                                    tabRowPage.unselectedIcon
                                }, contentDescription = tabRowPage.title
                            )
                        }
                    )
                }
            }
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
//                    .weight(1f),
            ) {
                GridTabs()
            }
        }

}

enum class TabRowPage(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    Shop(
        "Shop",
        Icons.Outlined.ShoppingCart,
        Icons.Filled.ShoppingCart
    ),

    Favorite(
        "Fav",
        Icons.Outlined.FavoriteBorder,
        Icons.Filled.FavoriteBorder
    ),

    Profile(
        "Man",
        Icons.Outlined.Man,
        Icons.Filled.Man
    ),

    Boom(
        "Boom",
        Icons.Outlined.AcUnit,
        Icons.Filled.AcUnit
    ),

    Cry(
        "Cry",
        Icons.Outlined.AccessAlarm,
        Icons.Filled.AccessAlarm
    )
}
