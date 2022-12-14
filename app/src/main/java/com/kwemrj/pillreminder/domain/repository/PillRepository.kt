package com.kwemrj.pillreminder.domain.repository

import com.kwemrj.pillreminder.core.Resource
import com.kwemrj.pillreminder.data.local.entity.MedicationEntity
import com.kwemrj.pillreminder.data.local.entity.ReminderEntity
import com.kwemrj.pillreminder.data.local.entity.ReminderWithMedication
import kotlinx.coroutines.flow.Flow

interface PillRepository {

    suspend fun insertMedication(medication: MedicationEntity) : Int

    suspend fun insertReminders(reminders: List<ReminderEntity>)

    fun getListOfReminders() : Flow<List<ReminderEntity>>

    fun getListOfMedications() :Flow<List<MedicationEntity>>

    fun getListOfMedicationsWithReminders() :Flow<List<ReminderWithMedication>>

    suspend fun getReminder(id : Int): ReminderEntity

    suspend fun updateReminder(reminder : ReminderEntity)
}