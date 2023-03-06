package com.kwemrj.pillreminder.domain.use_cases

import com.kwemrj.pillreminder.data.local.entity.MedicationEntity
import com.kwemrj.pillreminder.data.local.entity.ReminderWithMedication
import com.kwemrj.pillreminder.domain.repository.PillRepository

class DeleteMedicineWithDatesUseCase(
    private val repository: PillRepository
) {

    suspend operator fun invoke(drugId: Int) {
        repository.removeMedication(drugId)
    }

}