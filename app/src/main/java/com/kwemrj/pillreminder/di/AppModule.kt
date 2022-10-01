package com.kwemrj.pillreminder.di

import android.app.Application
import androidx.room.Room
import com.kwemrj.pillreminder.data.local.PillDao
import com.kwemrj.pillreminder.data.local.PillDatabase
import com.kwemrj.pillreminder.data.local.PillDatabase.Companion.PILL_DATABASE
import com.kwemrj.pillreminder.data.repository.PillRepositoryImpl
import com.kwemrj.pillreminder.domain.repository.PillRepository
import com.kwemrj.pillreminder.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): PillDatabase {
        return Room.databaseBuilder(
            app,
            PillDatabase::class.java,
            PILL_DATABASE
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(db: PillDatabase): PillDao {
        return db.pillDao()
    }

    @Provides
    @Singleton
    fun provideRepository(dao: PillDao): PillRepository {
        return PillRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: PillRepository): MedicationUseCases {
        return MedicationUseCases(
            insertMedicineWithDatesUseCase = InsertMedicineWithDatesUseCase(repository),
            getReminderListUseCase = GetReminderListUseCase(repository),
            getDrugWithReminderListUseCase = GetDrugWithReminderListUseCase(repository),
            updateReminderUseCase = UpdateReminderUseCase(repository),
            getReminderUseCase = GetReminderUseCase(repository),
            drugWithReminderListUseCase = DrugWithReminderListUseCase(repository)
        )
    }



}