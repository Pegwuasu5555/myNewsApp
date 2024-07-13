package com.mynewsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mynewsapp.ui.AllNews
import com.mynewsapp.ui.NewsDetails


@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.HOME
    ) {

                composable(route = Graph.HOME) { entry ->
                    AllNews(navController)
                }
                composable(route = "NewsDetails") {entry ->
                    NewsDetails(navController)
                }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val HOME = "home_graph"
}