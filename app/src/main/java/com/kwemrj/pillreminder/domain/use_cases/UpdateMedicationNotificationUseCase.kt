package com.kwemrj.pillreminder.domain.use_cases

import com.kwemrj.pillreminder.data.local.entity.MedicationEntity
import com.kwemrj.pillreminder.domain.repository.PillRepository

class UpdateMedicationNotificationUseCase(
    private val repository: PillRepository
) {

    suspend operator fun invoke(drugId : Int, isNotificationOn : Boolean) {
        repository.updateMedication(drugId, isNotificationOn)
    }

}