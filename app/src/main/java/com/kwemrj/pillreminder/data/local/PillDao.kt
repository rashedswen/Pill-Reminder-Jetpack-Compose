package com.kwemrj.pillreminder.data.local

import androidx.room.*
import com.kwemrj.pillreminder.data.local.entity.MedicationEntity
import com.kwemrj.pillreminder.data.local.entity.ReminderEntity
import com.kwemrj.pillreminder.data.local.entity.ReminderWithMedication
import kotlinx.coroutines.flow.Flow

const val QUERY_FOR_REMINDER_WITH_MEDICATION =
    "SELECT MedicationEntity.id as drugId, MedicationEntity.name as drugName, MedicationEntity.inventory as inventory," +
            "ReminderEntity.id as reminderId, ReminderEntity.takeStatus as takeStatus,  " +
            "ReminderEntity.time as time , MedicationEntity.form as medicationForm, " +
            "MedicationEntity.isNotificationOn as isNotificationOn " +
            "FROM ReminderEntity JOIN MedicationEntity " +
            "ON MedicationEntity.id = ReminderEntity.pill_id"

@Dao
interface PillDao {


    // Medication CRUD
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMedication(medication: MedicationEntity): Long

    @Query("UPDATE medicationentity set isNotificationOn = :isNotificationOn WHERE id = :drugId")
    suspend fun updateMedication(drugId: Int, isNotificationOn: Boolean)

    @Query("DELETE FROM medicationentity WHERE id = :medicationId")
    suspend fun removeMedication(medicationId: Int)

    @Query("DELETE FROM reminderentity WHERE pill_id = :medicationId")
    suspend fun removeMedicationReminders(medicationId: Int)

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
        QUERY_FOR_REMINDER_WITH_MEDICATION
    )
    fun getDrugWithReminderList(): Flow<List<ReminderWithMedication>>

    @Query(
        "SELECT MedicationEntity.id as drugId, MedicationEntity.name as drugName, MedicationEntity.inventory as inventory," +
                "ReminderEntity.id as reminderId, ReminderEntity.takeStatus as takeStatus,  " +
                "ReminderEntity.time as time , MedicationEntity.form as medicationForm, " +
                "MedicationEntity.isNotificationOn as isNotificationOn " +
                "FROM ReminderEntity JOIN MedicationEntity ON ReminderEntity.pill_id = MedicationEntity.id " +
                "WHERE MedicationEntity.id = :id"
    )
    fun getDrugWithReminder(id: Int): List<ReminderWithMedication>

    @Query(
        QUERY_FOR_REMINDER_WITH_MEDICATION
    )
    suspend fun getDrugWithReminderListS(): List<ReminderWithMedication>


}