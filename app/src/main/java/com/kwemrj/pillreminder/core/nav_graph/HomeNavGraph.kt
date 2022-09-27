package com.kwemrj.pillreminder.core.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kwemrj.pillreminder.presentation.reminder.ReminderHomeScreen

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Screen.Home.route,
        route = HOME_ROUTE
    ) {
        composable(
            route = Screen.Home.route
        ){
            ReminderHomeScreen(
                navController
            )
        }
    }
}