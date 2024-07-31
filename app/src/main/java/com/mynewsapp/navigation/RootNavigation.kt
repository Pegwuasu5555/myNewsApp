package com.mynewsapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.mynewsapp.ui.AllNews
import com.mynewsapp.ui.Categories
import com.mynewsapp.ui.Login
import com.mynewsapp.ui.NewsDetails
import com.mynewsapp.ui.Register
import com.mynewsapp.viewmodels.NewsViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Screens.LoginScreen.route
    ) {
        composable(route = Screens.LoginScreen.route) { entry ->
            val viewModel = entry.sharedViewModel<NewsViewModel>(navController)
            Login(navController, viewModel)
        }
        composable(route = Screens.RegisterScreen.route) { entry ->
            val viewModel = entry.sharedViewModel<NewsViewModel>(navController)
            Register(navController, viewModel)
        }

        composable(route = Screens.AllNewsScreen.route) { entry ->
            val viewModel = entry.sharedViewModel<NewsViewModel>(navController)
            AllNews(navController, viewModel)
        }
        composable(route = Screens.NewsDetailsScreen.route,
            arguments = listOf(navArgument("newsId") { type = NavType.StringType })
        ) { entry ->
            val viewModel = entry.sharedViewModel<NewsViewModel>(navController)
            val newsId = entry.arguments?.getString("newsId")
            NewsDetails(navController, viewModel, newsId)
        }
        composable(route = Screens.NewsCategoriesScreen.route,
            arguments = listOf(navArgument("sourceName") { type = NavType.StringType })) { entry ->
            val viewModel = entry.sharedViewModel<NewsViewModel>(navController)
            val sourceName = entry.arguments?.getString("sourceName")
            Categories(navController, viewModel, sourceName)
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val HOME = "home_graph"
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController,
): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}