package com.kwemrj.pillreminder.domain.use_cases

import com.kwemrj.pillreminder.data.local.entity.MedicationEntity
import com.kwemrj.pillreminder.data.local.entity.ReminderEntity
import com.kwemrj.pillreminder.domain.model.Reminder
import com.kwemrj.pillreminder.domain.repository.PillRepository

class GetMedicationUseCase(
    private val repository: PillRepository
) {

    suspend operator fun invoke(id: Int) : MedicationEntity {
        return repository.getMedication(id)
    }

}