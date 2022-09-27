package com.kwemrj.pillreminder.presentation.add_reminder

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kwemrj.pillreminder.data.local.entity.MedicationEntity
import com.kwemrj.pillreminder.domain.use_cases.MedicationUseCases
import com.kwemrj.pillreminder.presentation.add_reminder.util.IntervalInTimes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddReminderViewModel @Inject constructor(
    private val useCases: MedicationUseCases,
) : ViewModel() {

    private val _state = MutableStateFlow(AddReminderStates())
    val state: StateFlow<AddReminderStates> = _state

    private val _eventFlow = MutableSharedFlow<AddReminderUiEvents>()
    val eventFlow: SharedFlow<AddReminderUiEvents> = _eventFlow.asSharedFlow()

    private val _timesList = mutableStateListOf<Long>()
    val timesList: List<Long> = _timesList


    fun onEvent(event: AddReminderEvents) {
        when (event) {
            is AddReminderEvents.MedicineNameChange -> {
                _state.update { it.copy(medicineName = event.medicineName) }
            }
            is AddReminderEvents.MedicineInventoryChange -> {
                _state.update { it.copy(medicationInventory = event.inventory) }
            }
            is AddReminderEvents.MedicineNumberOfDosesChange -> {
                _state.update { it.copy(medicationNumberOfDoses = event.numberOfDoses) }
            }
            is AddReminderEvents.MedicineFormSelect -> {
                _state.update { it.copy(medicationForm = event.medicationForm) }
            }
            is AddReminderEvents.StartDateSelect -> {
                _state.update { it.copy(startDate = event.date) }
            }
            is AddReminderEvents.EndDateSelect -> {
                _state.update { it.copy(endDate = event.date) }
            }
            is AddReminderEvents.NewTimeAdd -> {
                _timesList.add(event.date)
                _state.update { it.copy(times = timesList) }
                Log.d("List", state.value.times.toList().toString())
            }
            is AddReminderEvents.SaveMedicineReminder -> {
                viewModelScope.launch {
                    val medicine = state.value
                    useCases.insertMedicineWithDatesUseCase(
                        MedicationEntity(
                            name = medicine.medicineName,
                            form = medicine.medicationForm,
                            startDate = medicine.startDate ?: Calendar.getInstance().timeInMillis,
                            endDate = medicine.endDate
                                ?: (Calendar.getInstance().timeInMillis + 2592000000),
                            intervalBetweenDoses =
                            if (medicine.intervalBetweenDoses.isNotEmpty())
                                medicine.intervalBetweenDoses.toInt()
                            else 24,
                            inventory = medicine.medicationInventory.toInt(),
                            dosesPerTime = medicine.medicationNumberOfDoses.toInt(),
                        ),
                        times = medicine.times,
                        intervalInTimes = state.value.intervalInTimes
                    )

                }
            }
            is AddReminderEvents.IntervalBetweenDosesChange -> {
                _state.update {
                    it.copy(intervalBetweenDoses = event.hours)
                }
            }
            is AddReminderEvents.RemoveTime -> {
                _timesList.removeAt(event.index)
                _state.update {
                    it.copy(times = timesList)
                }
            }
            is AddReminderEvents.EditTime -> {
                _timesList[event.index] = event.time
            }
            is AddReminderEvents.ChangeInterval -> {
                _state.update {
                    if (event.intervalInTimes == IntervalInTimes.EveryXHour && state.value.times.size > 0) {
                        _timesList.removeRange(0, timesList.lastIndex)
                    }
                    var intervalBetweenDosesByIntervalType = ""
                    if (state.value.intervalInTimes == IntervalInTimes.SpecificHourOfDay){
                        intervalBetweenDosesByIntervalType = "24"
                    } else if (state.value.intervalInTimes == IntervalInTimes.EveryXHour){
                        intervalBetweenDosesByIntervalType = state.value.intervalBetweenDoses
                    }
                    it.copy(
                        intervalInTimes = event.intervalInTimes,
                        times = timesList,
                        intervalBetweenDoses = intervalBetweenDosesByIntervalType
                    )
                }
            }
        }
    }

    private fun checkFields() {

    }


    sealed class AddReminderUiEvents {}

}