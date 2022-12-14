package com.kwemrj.pillreminder.data.repository

import com.kwemrj.pillreminder.data.local.PillDao
import com.kwemrj.pillreminder.data.local.entity.MedicationEntity
import com.kwemrj.pillreminder.data.local.entity.ReminderEntity
import com.kwemrj.pillreminder.data.local.entity.ReminderWithMedication
import com.kwemrj.pillreminder.domain.repository.PillRepository
import kotlinx.coroutines.flow.Flow

class PillRepositoryImpl(
    private val dao: PillDao
) : PillRepository {
    override suspend fun insertMedication(medication: MedicationEntity) : Int {
        return dao.insertMedication(medication).toInt()
    }

    override suspend fun insertReminders(reminders: List<ReminderEntity>) {
        dao.insertOrUpdateReminder(reminders)
    }

    override fun getListOfReminders(): Flow<List<ReminderEntity>> {
        return dao.getReminderList()
    }

    override suspend fun getReminder(id : Int): ReminderEntity {
        return dao.getReminder(id)
    }

    override suspend fun updateReminder(reminder : ReminderEntity) {
        dao.updateReminder(reminder)
    }

    override fun getListOfMedications(): Flow<List<MedicationEntity>> {
        return dao.getMedicationsList()
    }

    override fun getListOfMedicationsWithReminders(): Flow<List<ReminderWithMedication>> {
        return dao.getDrugWithReminderList()
    }


}