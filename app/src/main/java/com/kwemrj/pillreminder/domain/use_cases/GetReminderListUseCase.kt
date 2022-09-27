package com.kwemrj.pillreminder.domain.use_cases

import com.kwemrj.pillreminder.domain.model.Reminder
import com.kwemrj.pillreminder.domain.repository.PillRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetReminderListUseCase(
    private val repository: PillRepository
) {

    operator fun invoke(): Flow<List<Reminder>> {
        return repository.getListOfReminders()
            .map { it.map { reminderEntity -> reminderEntity.toReminder() } }
    }

}