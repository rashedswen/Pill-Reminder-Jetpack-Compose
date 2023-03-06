package com.kwemrj.pillreminder.domain.use_cases

import com.kwemrj.pillreminder.domain.model.Medication
import com.kwemrj.pillreminder.domain.repository.PillRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMedicationListUseCase(
    private val repository: PillRepository
) {

    operator fun invoke(): Flow<List<Medication>> {
        return repository.getListOfMedications()
            .map { medicationEntityList -> medicationEntityList.map { it.toMedication() } }
    }

}