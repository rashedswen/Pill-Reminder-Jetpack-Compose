package com.kwemrj.pillreminder.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kwemrj.pillreminder.core.enums.TakeStatus

data class Reminder(
    val id : Int = 0,
    val pill_id : Int,
    val time : Long,
    val takeStatus : TakeStatus = TakeStatus.Pending
)
