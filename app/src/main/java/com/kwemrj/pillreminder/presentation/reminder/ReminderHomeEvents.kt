package com.kwemrj.pillreminder.presentation.reminder

import com.kwemrj.pillreminder.data.local.entity.ReminderWithMedication
import com.kwemrj.pillreminder.domain.model.Reminder

sealed interface ReminderHomeEvents {
    data class OnDayChange(val startOfDay: Long) : ReminderHomeEvents
    data class ReminderDetails(val reminder: ReminderWithMedication) : ReminderHomeEvents
    data class OnTakeRequest(val reminder: ReminderWithMedication) : ReminderHomeEvents
    data class OnSkipRequest(val reminder: ReminderWithMedication) : ReminderHomeEvents
}