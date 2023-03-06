package com.kwemrj.pillreminder.data.broadcast_reciver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.kwemrj.pillreminder.core.setAlarm
import com.kwemrj.pillreminder.domain.use_cases.MedicationUseCases
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@AndroidEntryPoint
class OnBootBroadcast : BroadcastReceiver() {

    @Inject
    lateinit var useCases: MedicationUseCases

    override fun onReceive(context: Context?, intent: Intent?) {
        
        val currentTime = Calendar.getInstance().timeInMillis

        CoroutineScope(Dispatchers.Main).launch {
            val remindersList = useCases.drugWithReminderListUseCase()
            for (reminder in remindersList) {
                if (reminder.time >= currentTime) {
                    setAlarm(reminder, context)
                }
            }
        }

    }
}