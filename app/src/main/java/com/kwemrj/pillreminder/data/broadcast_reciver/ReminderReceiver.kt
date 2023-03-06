package com.kwemrj.pillreminder.data.broadcast_reciver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.kwemrj.pillreminder.core.sendNotification
import com.kwemrj.pillreminder.data.local.entity.ReminderWithMedication

class ReminderReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {

        val drugReminder = intent?.getSerializableExtra("drug_reminder") as ReminderWithMedication

        val notificationManager = context?.let {
            ContextCompat.getSystemService(
                it,
                NotificationManager::class.java,
            ) as NotificationManager
        }

        notificationManager?.sendNotification(
            messageBody = "it's time for get ${drugReminder.drugName} ",
            context,
            drugReminder
        )

    }

}