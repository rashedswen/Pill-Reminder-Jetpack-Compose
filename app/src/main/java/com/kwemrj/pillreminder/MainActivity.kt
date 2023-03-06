package com.kwemrj.pillreminder

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.kwemrj.pillreminder.core.nav_graph.HOME_ROUTE
import com.kwemrj.pillreminder.core.nav_graph.Screen
import com.kwemrj.pillreminder.core.nav_graph.SetupNavGraph
import com.kwemrj.pillreminder.core.nav_graph.bottom_nav_graph.MainScreen
import com.kwemrj.pillreminder.ui.theme.PillReminderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PillReminderTheme {
                SetupNavGraph(
                    navController = rememberNavController(),
                    startRoute = HOME_ROUTE
                )
            }
        }
    }

}