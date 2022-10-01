package com.kwemrj.pillreminder.core

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.kwemrj.pillreminder.MainActivity
import com.kwemrj.pillreminder.R
import com.kwemrj.pillreminder.data.local.entity.ReminderWithMedication

fun NotificationManager.sendNotification(messageBody: String, context: Context, reminder: ReminderWithMedication) {

    val clickNotificationIntent = Intent(context, MainActivity::class.java)
    clickNotificationIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
    val pendingIntent: PendingIntent = PendingIntent.getActivity(
        context, 0, clickNotificationIntent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )


    val builder = NotificationCompat.Builder(
        context,
        "reminder_channel"
    )
        .setSmallIcon(R.drawable.ic_tablet)
        .setContentTitle("get your pill ${reminder.drugName}")
        .setContentText(messageBody)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

    notify(reminder.reminderId, builder.build())
}
fun NotificationManager.sendBootNotification(messageBody: String, context: Context, id: Int) {

    val clickNotificationIntent = Intent(context, MainActivity::class.java)
    clickNotificationIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
    val pendingIntent: PendingIntent = PendingIntent.getActivity(
        context, 0, clickNotificationIntent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )


    val builder = NotificationCompat.Builder(
        context,
        "reminder_channel"
    )
        .setSmallIcon(R.drawable.ic_tablet)
        .setContentTitle("On Boot Notification")
        .setContentText(messageBody)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

    notify(999999, builder.build())
}