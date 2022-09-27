package com.kwemrj.pillreminder.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kwemrj.pillreminder.core.enums.TakeStatus
import com.kwemrj.pillreminder.domain.model.Reminder


@Entity
data class ReminderEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val pill_id: Int,
    val time: Long,
    val takeStatus: TakeStatus = TakeStatus.Pending
) {
    fun toReminder(): Reminder {
        return Reminder(id, pill_id, time, takeStatus)
    }
}
