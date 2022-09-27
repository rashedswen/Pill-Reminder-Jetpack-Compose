package com.kwemrj.pillreminder.domain.use_cases

import com.kwemrj.pillreminder.data.local.entity.ReminderEntity
import com.kwemrj.pillreminder.domain.repository.PillRepository

class UpdateReminderUseCase(
    private val repository: PillRepository
) {

    suspend operator fun invoke(reminder: ReminderEntity) {
        repository.updateReminder(reminder)
    }

}