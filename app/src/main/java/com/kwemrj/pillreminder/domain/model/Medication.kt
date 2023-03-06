package com.kwemrj.pillreminder.domain.model

import com.kwemrj.pillreminder.core.enums.MedicationForm
import com.kwemrj.pillreminder.core.enums.MedicineStatus

data class Medication(
    val id : Int,
    val name : String,
    val form : MedicationForm,
    val intervalBetweenDoses: Int,
    val inventory: Int,
    val dosesPerTime: Int,
    val status : MedicineStatus = MedicineStatus.Active,
    val startDate : Long,
    val endDate : Long,
    val isNotificationOn : Boolean = true
)