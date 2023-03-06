package com.kwemrj.pillreminder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kwemrj.pillreminder.data.local.converters.Converters
import com.kwemrj.pillreminder.data.local.entity.MedicationEntity
import com.kwemrj.pillreminder.data.local.entity.ReminderEntity

@Database(entities = [ReminderEntity::class , MedicationEntity::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PillDatabase : RoomDatabase(){

    abstract fun pillDao() : PillDao


    companion object {
        const val PILL_DATABASE = "Pill_database"
    }
}