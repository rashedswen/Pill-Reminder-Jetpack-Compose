package com.kwemrj.pillreminder.core.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
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
            route = Screen.MedicineDetails.route
        ) {
            ReminderDetailsScreen(
                navController = navController
            )
        }
    }
}