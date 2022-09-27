package com.kwemrj.pillreminder.presentation.add_reminder

import com.kwemrj.pillreminder.core.enums.MedicationForm
import com.kwemrj.pillreminder.presentation.add_reminder.util.IntervalInTimes

data class AddReminderStates(
    val medicineName: String = "",
    val medicationInventory: String = "",
    val medicationNumberOfDoses: String = "",
    val medicationForm: MedicationForm = MedicationForm.Capsule,
    val startDate: Long? = null,
    val endDate: Long? = null,
    val intervalBetweenDoses : String = "",
    val times : List<Long> = listOf(),
    val intervalInTimes: IntervalInTimes = IntervalInTimes.EveryXHour,
)
