package com.mynewsapp.navigation

//enum class Screens {
//    AllNewsScreen,
//    NewsDetailsScreen,
//    NewsCategoriesScreen,
//}

enum class Screens(val route: String) {
    LoginScreen("login"),
    RegisterScreen("regsiter"),
    AllNewsScreen("allNews"),
    NewsDetailsScreen("newsDetails/{newsId}"), // Define the parameter in the route
    NewsCategoriesScreen("newsCategories/{sourceName}")
}

