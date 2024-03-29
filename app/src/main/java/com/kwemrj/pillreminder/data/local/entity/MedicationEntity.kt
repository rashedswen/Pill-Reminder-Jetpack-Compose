package com.kwemrj.pillreminder.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kwemrj.pillreminder.core.enums.MedicationForm
import com.kwemrj.pillreminder.core.enums.MedicineStatus
import com.kwemrj.pillreminder.domain.model.Medication

@Entity
data class MedicationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val form: MedicationForm,
    val intervalBetweenDoses: Int,
    val inventory: Int,
    val dosesPerTime: Int,
    val status: MedicineStatus = MedicineStatus.Active,
    val startDate: Long,
    val endDate: Long,
    val isNotificationOn: Boolean = true
) {
    fun toMedication(): Medication {
        return Medication(
            id,
            name,
            form,
            intervalBetweenDoses,
            inventory,
            dosesPerTime,
            status,
            startDate,
            endDate,
            isNotificationOn
        )
    }
}