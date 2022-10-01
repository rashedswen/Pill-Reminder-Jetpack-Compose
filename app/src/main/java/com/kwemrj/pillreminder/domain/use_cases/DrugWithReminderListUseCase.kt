package com.kwemrj.pillreminder.domain.use_cases

import com.kwemrj.pillreminder.data.local.entity.ReminderWithMedication
import com.kwemrj.pillreminder.domain.model.Reminder
import com.kwemrj.pillreminder.domain.repository.PillRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetDrugWithReminderListUseCase(
    private val repository: PillRepository
) {

    operator fun invoke(): Flow<List<ReminderWithMedication>> {
        return repository.getListOfMedicationsWithReminders()
    }

}