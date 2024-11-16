package com.example.pickingbomb.ui.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pickingbomb.ui.view.search.ui.ImageSearchRequest
import com.example.pickingbomb.ui.view.search.ui.ImageSearchResult

@Composable
fun NavGraph() {
    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = "search-screen"
    ) {
        addNavGraph(navController)
    }
}

// Extension function on NavGraphBuilder to define the navigation graph
fun NavGraphBuilder.addNavGraph(controller: NavController) {
    composable("search-screen") {
        ImageSearchRequest(controller)
    }

    composable("result-screen") {
        ImageSearchResult(controller) // Your composable for result screen
    }
}
