package com.kwemrj.pillreminder.core.nav_graph.bottom_nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kwemrj.pillreminder.core.nav_graph.HOME_ROUTE
import com.kwemrj.pillreminder.core.nav_graph.Screen
import com.kwemrj.pillreminder.core.nav_graph.addNavGraph
import com.kwemrj.pillreminder.core.nav_graph.homeNavGraph
import com.kwemrj.pillreminder.presentation.inventory.InventoryScreen
import com.kwemrj.pillreminder.presentation.reminder.ReminderHomeScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        route = HOME_ROUTE
    ) {
        composable(
            route = Screen.Home.route
        ) {
            ReminderHomeScreen(
                navController
            )
        }

        composable(
            route = Screen.Inventory.route
        ) {
            InventoryScreen(
                navController
            )
        }

        addNavGraph(navController)
        homeNavGraph(navController)
    }
}