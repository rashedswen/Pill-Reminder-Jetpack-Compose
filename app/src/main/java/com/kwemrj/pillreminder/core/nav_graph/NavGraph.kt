package com.kwemrj.pillreminder.core.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startRoute : String,
) {
    NavHost(
        navController = navController,
        startDestination = startRoute,
        route = ROOT_ROUTE
    ) {
        addNavGraph(navController)
        homeNavGraph(navController)
    }
}