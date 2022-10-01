package com.kwemrj.pillreminder.data.local

import androidx.room.*
import com.kwemrj.pillreminder.data.local.entity.MedicationEntity
import com.kwemrj.pillreminder.data.local.entity.ReminderEntity
import com.kwemrj.pillreminder.data.local.entity.ReminderWithMedication
import kotlinx.coroutines.flow.Flow

@Dao
interface PillDao {

    // Medication CRUD
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMedication(medication: MedicationEntity): Long

    @Query("SELECT * FROM MedicationEntity")
    fun getMedicationsList(): Flow<List<MedicationEntity>>

    @Query("SELECT * FROM MedicationEntity WHERE id = :id")
    suspend fun getMedicationInfo(id: Int): MedicationEntity

    // reminder CRUD
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateReminder(reminders: List<ReminderEntity>)

    @Query("SELECT * FROM ReminderEntity WHERE id = :id")
    suspend fun getReminder(id: Int): ReminderEntity

    @Update
    suspend fun updateReminder(reminder: ReminderEntity)

    @Query("SELECT * FROM ReminderEntity")
    fun getReminderList(): Flow<List<ReminderEntity>>

    // drug with reminders

    @Query(
        "SELECT MedicationEntity.id as drugId, MedicationEntity.name as drugName, " +
                "ReminderEntity.id as reminderId, ReminderEntity.takeStatus as takeStatus,  " +
                "ReminderEntity.time as time , MedicationEntity.form as medicationForm " +
                "FROM ReminderEntity JOIN MedicationEntity " +
                "ON MedicationEntity.id = ReminderEntity.pill_id"
    )
    fun getDrugWithReminderList(): Flow<List<ReminderWithMedication>>
    @Query(
        "SELECT MedicationEntity.id as drugId, MedicationEntity.name as drugName, " +
                "ReminderEntity.id as reminderId, ReminderEntity.takeStatus as takeStatus,  " +
                "ReminderEntity.time as time , MedicationEntity.form as medicationForm " +
                "FROM ReminderEntity JOIN MedicationEntity " +
                "ON MedicationEntity.id = ReminderEntity.pill_id"
    )
    suspend fun getDrugWithReminderListS(): List<ReminderWithMedication>


}