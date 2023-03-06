package com.kwemrj.pillreminder.domain.use_cases

import android.util.Log
import androidx.core.os.BuildCompat
import com.kwemrj.pillreminder.core.Constants.HOUR_IN_MILLIS
import com.kwemrj.pillreminder.core.enums.TakeStatus
import com.kwemrj.pillreminder.data.local.entity.MedicationEntity
import com.kwemrj.pillreminder.data.local.entity.ReminderEntity
import com.kwemrj.pillreminder.domain.repository.PillRepository
import com.kwemrj.pillreminder.presentation.add_reminder.util.IntervalInTimes
import java.util.Calendar
import kotlin.time.Duration.Companion.hours

class InsertMedicineWithDatesUseCase(
    private val repository: PillRepository
) {

    suspend operator fun invoke(
        medicationEntity: MedicationEntity,
        times: List<Long>,
        intervalInTimes: IntervalInTimes
    ) {
        val medicationId = repository.insertMedication(medicationEntity)
        if (intervalInTimes != IntervalInTimes.AsNeeded) {
            repository.insertReminders(
                getListOfReminderDates(
                    id = medicationId,
                    times = times,
                    endDate = medicationEntity.endDate.setToStartOfDay(),
                    interval = medicationEntity.intervalBetweenDoses,
                    startDate = medicationEntity.startDate.setToStartOfDay(),
                )
            )
        }
    }

    private fun Long.setToStartOfDay() : Long{
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = this
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        calendar.set(year, month, dayOfMonth,0,0,0)
        return calendar.timeInMillis
    }
    private fun getListOfReminderDates(
        id: Int,
        times: List<Long>,
        endDate: Long,
        interval: Int,
        startDate: Long
    ): MutableList<ReminderEntity> {
        val reminderList = mutableListOf<ReminderEntity>()
        val intervalBetweenDosesInMillis = interval * HOUR_IN_MILLIS
        for (time in times) {
            var newTime = time + startDate
            while (newTime <= endDate) {
                reminderList.add(
                    ReminderEntity(
                        pill_id = id,
                        time = newTime,
                        takeStatus = if (newTime < Calendar.getInstance().timeInMillis) {
                            TakeStatus.Taken
                        } else {
                            TakeStatus.Pending
                        }
                    )
                )
                newTime += intervalBetweenDosesInMillis
            }
        }
        return reminderList
    }

}