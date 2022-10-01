package com.kwemrj.pillreminder.presentation.reminder_details

import androidx.lifecycle.ViewModel
import com.kwemrj.pillreminder.domain.use_cases.MedicationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReminderDetailsViewModel @Inject constructor(
    private val useCases: MedicationUseCases
) : ViewModel() {

}