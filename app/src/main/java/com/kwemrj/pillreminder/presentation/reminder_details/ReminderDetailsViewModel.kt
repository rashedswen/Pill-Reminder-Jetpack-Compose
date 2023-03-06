package com.kwemrj.pillreminder.presentation.reminder_details

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.kwemrj.pillreminder.PillApplication
import com.kwemrj.pillreminder.core.enums.TakeStatus
import com.kwemrj.pillreminder.core.nav_graph.DETAIL_ARGUMENT_REMINDER
import com.kwemrj.pillreminder.core.removeAlarm
import com.kwemrj.pillreminder.core.setAlarm
import com.kwemrj.pillreminder.data.local.entity.MedicationEntity
import com.kwemrj.pillreminder.data.local.entity.ReminderWithMedication
import com.kwemrj.pillreminder.domain.use_cases.MedicationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class ReminderDetailsViewModel @Inject constructor(
    private val useCases: MedicationUseCases,
    savedStateHandle: SavedStateHandle,
    application: Application
) : AndroidViewModel(application) {

    private val _state = MutableStateFlow(ReminderDetailsStates())
    val state: StateFlow<ReminderDetailsStates> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    lateinit var medication: MedicationEntity

    private val context = getApplication<PillApplication>().applicationContext

    init {
        savedStateHandle.get<Int>(DETAIL_ARGUMENT_REMINDER).let { reminderId ->
            viewModelScope.launch(Dispatchers.IO) {
                Log.d("id passed ", reminderId.toString())

                medication = useCases.getMedicationUseCase(reminderId!!)
                val reminders: List<ReminderWithMedication> = try {
                    useCases.getDrugWithReminderDetailsUseCase(reminderId)
                        .apply {
                            println(this)
                        }
                        .filter { it.takeStatus == TakeStatus.Taken }
                        .apply {
                            println(this)
                        }
                } catch (e: Exception) {
                    Log.d("e", e.message.toString())
                    emptyList()
                }

                val takenPills = reminders.count { it.takeStatus == TakeStatus.Taken }
                val missedPills = reminders.count { it.takeStatus == TakeStatus.Missed }
                val skippedPills = reminders.count { it.takeStatus == TakeStatus.Skipped }
                _state.update { states ->
                    states.copy(
                        medicineName = medication.name,
                        medicineForm = medication.form,
                        takenPills = takenPills,
                        missedPills = missedPills,
                        skippedPills = skippedPills,
                        inventoryPills = medication.inventory.minus(takenPills) ?: 0,
                        lastThreePills = reminders,
                        isNotificationOn = medication.isNotificationOn
                    )
                }
            }
        }
    }

    fun onEvent(event: ReminderDetailsEvents) {
        when (event) {
            is ReminderDetailsEvents.DeleteMedicationWithReminder -> {
                viewModelScope.launch {
                    useCases.deleteMedicineWithDatesUseCase(medication.id)
                    _eventFlow.emit(UIEvent.ReminderDeleted)
                }
            }

            is ReminderDetailsEvents.TurnReminderNotification -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _state.update {
                        it.copy(
                            isNotificationOn = !it.isNotificationOn
                        )
                    }
                    val reminders = useCases.drugWithReminderListUseCase()
                    reminders.filter { it.time >= Calendar.getInstance().timeInMillis && it.drugId == medication.id }
                        .forEach {
                            if (state.value.isNotificationOn) {
                                setAlarm(it, context)
                                _eventFlow.emit(UIEvent.NotificationStateChange(message = "Notification On"))
                            } else {
                                removeAlarm(it, context)
                                _eventFlow.emit(UIEvent.NotificationStateChange(message = "Notification Off"))

                            }

                        }
                    useCases.updateMedicationNotificationUseCase(
                        drugId = medication.id,
                        isNotificationOn = state.value.isNotificationOn
                    )
                    Log.d("done", "done ${state.value.isNotificationOn}")
                }
            }
        }
    }

    sealed interface UIEvent {
        object ReminderDeleted : UIEvent
        data class NotificationStateChange(val message: String) : UIEvent
    }

}

fun List<ReminderWithMedication>.takeLastPills(n: Int): List<ReminderWithMedication> {
    val newList = mutableListOf<ReminderWithMedication>()
    this.forEach {
        if (it.takeStatus == TakeStatus.Taken) {
            newList.add(it)
        }
    }
    return newList.toList().takeLast(n)
}