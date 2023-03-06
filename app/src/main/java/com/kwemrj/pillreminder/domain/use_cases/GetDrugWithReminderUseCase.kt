package com.kwemrj.pillreminder.domain.use_cases

import com.kwemrj.pillreminder.data.local.entity.ReminderWithMedication
import com.kwemrj.pillreminder.domain.repository.PillRepository


class GetDrugWithReminderDetailsUseCase(
    private val repository: PillRepository
) {

    suspend operator fun invoke(id: Int): List<ReminderWithMedication> {
        return repository.medicationsWithReminders(id)
    }

}