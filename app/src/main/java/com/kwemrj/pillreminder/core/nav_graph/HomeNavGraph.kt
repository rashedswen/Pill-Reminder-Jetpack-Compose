package com.kwemrj.pillreminder.core.nav_graph

import android.util.Log
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.kwemrj.pillreminder.presentation.inventory.InventoryScreen
import com.kwemrj.pillreminder.presentation.reminder.ReminderHomeScreen
import com.kwemrj.pillreminder.presentation.reminder_details.ReminderDetailsScreen

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController
) {
    navigation(
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
    }
}