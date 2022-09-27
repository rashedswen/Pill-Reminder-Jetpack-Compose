package com.kwemrj.pillreminder.presentation.reminder

import com.kwemrj.pillreminder.data.local.entity.ReminderWithMedication
import com.kwemrj.pillreminder.domain.model.Reminder

data class ReminderHomeStates(
    val reminderList : List<ReminderWithMedication> = emptyList(),
    val selectedDay : Long = 0L
)
