package com.kwemrj.pillreminder.domain.use_cases

import com.kwemrj.pillreminder.core.Constants.HOUR_IN_MILLIS
import com.kwemrj.pillreminder.core.enums.TakeStatus
import com.kwemrj.pillreminder.data.local.entity.MedicationEntity
import com.kwemrj.pillreminder.data.local.entity.ReminderEntity
import com.kwemrj.pillreminder.domain.repository.PillRepository
import com.kwemrj.pillreminder.presentation.add_reminder.util.IntervalInTimes
import java.util.*

class InsertMedicineWithDatesUseCase(
    private val repository: PillRepository
) {

    suspend operator fun invoke(medicationEntity: MedicationEntity, times: List<Long>, intervalInTimes: IntervalInTimes) {
        val medicationId = repository.insertMedication(medicationEntity)
        if(intervalInTimes != IntervalInTimes.AsNeeded) {
            repository.insertReminders(
                getListOfReminderDates(
                    id = medicationId,
                    times = times,
                    endDate = medicationEntity.endDate,
                    interval = medicationEntity.intervalBetweenDoses,
                    startDate = medicationEntity.startDate,
                )
            )
        }
    }

    private fun getListOfReminderDates(
        id: Int,
        times: List<Long>,
        endDate: Long,
        interval: Int,
        startDate : Long,
    ): List<ReminderEntity> {
        val reminderList = mutableListOf<ReminderEntity>()
        val intervalBetweenDosesInMillis = interval * HOUR_IN_MILLIS
        for (i in times){
            var newTime = i + startDate
            var isFirstTime = true
            while (newTime <= endDate) {
                if (!isFirstTime){
                    newTime += intervalBetweenDosesInMillis
                }
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

                isFirstTime = false
            }
        }

        return reminderList.toList()
    }

}