package com.example.pickingbomb.ui.view.search.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.pickingbomb.ui.navgraph.NavGraph

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
           AppBar()
        }

    }
}


@Preview(showBackground = true)
@Composable
fun Preview(){
    val navController = rememberNavController()
    ImageSearchRequest(navController)
}






