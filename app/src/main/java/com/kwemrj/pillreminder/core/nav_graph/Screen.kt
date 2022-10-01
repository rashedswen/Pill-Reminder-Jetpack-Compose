package com.kwemrj.pillreminder.core.nav_graph

const val ROOT_ROUTE = "root"
const val HOME_ROUTE = "home"
const val ADD_EDIT_ROUTE = "add"

sealed class Screen(val route : String) {
    object Home: Screen("home_screen")
    object AddMedicine: Screen("add_medicine_screen")
    object MedicineDetails: Screen("medicine_details_screen")
}