package com.kwemrj.pillreminder.core

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.kwemrj.pillreminder.MainActivity
import com.kwemrj.pillreminder.data.broadcast_reciver.ReminderReceiver
import com.kwemrj.pillreminder.data.local.entity.ReminderWithMedication
import java.util.Calendar

fun setAlarm(drugReminder: ReminderWithMedication, context: Context?) {

    // creating alarmManager instance
    val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    // adding intent and pending intent to go to AlarmReceiver Class in future
    val intent = Intent(context, ReminderReceiver::class.java)
    intent.putExtra("drug_reminder", drugReminder)
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        drugReminder.reminderId,
        intent,
        PendingIntent.FLAG_IMMUTABLE
    )

    // when using setAlarmClock() it displays a notification until alarm rings and when pressed it takes us to mainActivity
    val mainScreenIntent = Intent(context, MainActivity::class.java)
    val mainPendingIntent = PendingIntent.getActivity(
        context,
        drugReminder.reminderId,
        mainScreenIntent,
        PendingIntent.FLAG_IMMUTABLE
    )

    // creating clockInfo instance
    val clockInfo = AlarmManager.AlarmClockInfo(drugReminder.time, mainPendingIntent)

    // setting the alarm
    alarmManager.setAlarmClock(clockInfo, pendingIntent)

}

 fun removeAlarm(drugReminder: ReminderWithMedication, context: Context?){
    val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, ReminderReceiver::class.java)
    // It is not necessary to add putExtra
    intent.putExtra("drug_reminder", drugReminder)
    val pendingIntent = PendingIntent.getBroadcast(context, drugReminder.reminderId, intent, PendingIntent.FLAG_IMMUTABLE)
    alarmManager.cancel(pendingIntent)
}
