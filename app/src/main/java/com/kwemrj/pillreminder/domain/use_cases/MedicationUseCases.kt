package com.kwemrj.pillreminder.domain.use_cases

data class MedicationUseCases(
    val insertMedicineWithDatesUseCase: InsertMedicineWithDatesUseCase,
    val getReminderListUseCase: GetReminderListUseCase,
    val getDrugWithReminderListUseCase: GetDrugWithReminderListUseCase,
    val updateReminderUseCase: UpdateReminderUseCase,
    val getReminderUseCase: GetReminderUseCase,
    val getMedicationUseCase: GetMedicationUseCase,
    val drugWithReminderListUseCase: DrugWithReminderListUseCase,
    val getDrugWithReminderDetailsUseCase: GetDrugWithReminderDetailsUseCase,
    val getMedicationListUseCase: GetMedicationListUseCase,
    val deleteMedicineWithDatesUseCase: DeleteMedicineWithDatesUseCase,
    val updateMedicationNotificationUseCase: UpdateMedicationNotificationUseCase
)
