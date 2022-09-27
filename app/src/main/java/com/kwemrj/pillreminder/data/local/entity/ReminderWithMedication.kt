package com.kwemrj.pillreminder.data.local.entity

import com.kwemrj.pillreminder.core.enums.MedicationForm
import com.kwemrj.pillreminder.core.enums.TakeStatus

data class ReminderWithMedication(
    val drugId : Int,
    val reminderId: Int,
    val drugName : String,
    val takeStatus : TakeStatus,
    val medicationForm: MedicationForm,
    val time : Long
)
