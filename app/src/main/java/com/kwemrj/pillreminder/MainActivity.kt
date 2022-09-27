package com.kwemrj.pillreminder

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.kwemrj.pillreminder.core.nav_graph.HOME_ROUTE
import com.kwemrj.pillreminder.core.nav_graph.Screen
import com.kwemrj.pillreminder.core.nav_graph.SetupNavGraph
import com.kwemrj.pillreminder.ui.theme.PillReminderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PillReminderTheme {
                // A surface container using the 'background' color from the theme
                SetupNavGraph(
                    navController = rememberNavController(),
                    startRoute = HOME_ROUTE
                )
            }
        }
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Reminder"
            val descriptionText = "For getting medication reminder"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("reminder_channel", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}