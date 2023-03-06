package com.kwemrj.pillreminder.presentation.inventory

import com.kwemrj.pillreminder.domain.model.Medication

data class InventoryStates(
    val medicationsList: List<Medication> = emptyList()
)
