package com.kwemrj.pillreminder.domain.use_cases

import com.kwemrj.pillreminder.data.local.entity.ReminderEntity
import com.kwemrj.pillreminder.domain.model.Reminder
import com.kwemrj.pillreminder.domain.repository.PillRepository

class GetReminderUseCase(
    private val repository: PillRepository
) {

    suspend operator fun invoke(id: Int) : ReminderEntity {
        return repository.getReminder(id)
    }

}