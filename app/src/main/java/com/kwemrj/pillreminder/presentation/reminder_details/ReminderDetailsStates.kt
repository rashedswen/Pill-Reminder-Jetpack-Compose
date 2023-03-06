package com.kwemrj.pillreminder.presentation.reminder_details

import com.kwemrj.pillreminder.core.enums.MedicationForm
import com.kwemrj.pillreminder.data.local.entity.ReminderEntity
import com.kwemrj.pillreminder.data.local.entity.ReminderWithMedication

data class ReminderDetailsStates(
    val medicineName : String = "",
    val medicineForm: MedicationForm = MedicationForm.Tablet,
    val takenPills : Int = 0,
    val inventoryPills : Int = 0,
    val missedPills : Int = 0,
    val skippedPills : Int = 0,
    val isNotificationOn : Boolean = true,
    val lastThreePills : List<ReminderWithMedication> = emptyList(),
)