package com.kwemrj.pillreminder.data.broadcast_reciver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.kwemrj.pillreminder.core.sendNotification

class ReminderBroadcast : BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {

        val notificationManager = context?.let {
            ContextCompat.getSystemService(
                it,
                NotificationManager::class.java,
            ) as NotificationManager
        }

        notificationManager?.sendNotification(messageBody = "Notification Working", context)

    }

}