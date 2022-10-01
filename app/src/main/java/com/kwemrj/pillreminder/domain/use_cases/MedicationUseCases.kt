package com.kwemrj.pillreminder.domain.use_cases

data class MedicationUseCases(
    val insertMedicineWithDatesUseCase: InsertMedicineWithDatesUseCase,
    val getReminderListUseCase: GetReminderListUseCase,
    val getDrugWithReminderListUseCase: GetDrugWithReminderListUseCase,
    val updateReminderUseCase: UpdateReminderUseCase,
    val getReminderUseCase: GetReminderUseCase,
    val drugWithReminderListUseCase: DrugWithReminderListUseCase
)
