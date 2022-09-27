package com.kwemrj.pillreminder.core.nav_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kwemrj.pillreminder.presentation.add_reminder.AddReminderScreen

fun NavGraphBuilder.addNavGraph(
    navController: NavController
){
    navigation(
        startDestination = Screen.AddMedicine.route,
        route = ADD_EDIT_ROUTE
    ) {
        composable(
            route = Screen.AddMedicine.route
        ) {
            AddReminderScreen(
                navController
            )
        }
    }
}