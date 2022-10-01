package com.kwemrj.pillreminder.presentation.reminder

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kwemrj.pillreminder.core.enums.TakeStatus
import com.kwemrj.pillreminder.data.local.entity.ReminderEntity
import com.kwemrj.pillreminder.domain.use_cases.MedicationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ReminderHomeViewModel @Inject constructor(
    private val useCases: MedicationUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(ReminderHomeStates())
    val state: StateFlow<ReminderHomeStates> = _state

    private val _listOfDays = mutableStateListOf<Long>()
    val listOfDays: List<Long> = _listOfDays

    private val calendar = Calendar.getInstance()

    private var reminder: ReminderEntity? = null

    init {
        setCalendarToStartOfDay()
        _state.update {
            it.copy(selectedDay = calendar.timeInMillis)
        }
        daysList()
        getReminderListForDay()
        changeOldRemindersToMissed()
    }

    fun onEvent(event: ReminderHomeEvents) {
        when (event) {
            is ReminderHomeEvents.OnDayChange -> {
                _state.update {
                    it.copy(selectedDay = event.startOfDay)
                }
                Log.d("Calendar Day", event.startOfDay.toString())

                getReminderListForDay()
            }
            is ReminderHomeEvents.ReminderDetails -> {
                viewModelScope.launch {
                    reminder = useCases.getReminderUseCase(event.reminder.reminderId)
                }
            }
            is ReminderHomeEvents.OnSkipRequest -> {
                viewModelScope.launch {
                    useCases.updateReminderUseCase(
                        reminder = reminder!!.copy(
                            takeStatus = TakeStatus.Skipped
                        )
                    )
                }
            }
            is ReminderHomeEvents.OnTakeRequest -> {
                viewModelScope.launch {
                    useCases.updateReminderUseCase(
                        reminder = reminder!!.copy(
                            takeStatus = TakeStatus.Taken
                        )
                    )
                }
            }
        }
    }

    private fun changeOldRemindersToMissed() {
        useCases.getDrugWithReminderListUseCase()
            .onEach { listOfReminders ->
                listOfReminders.filter { it.time <= Calendar.getInstance().timeInMillis }
                    .forEach { reminder ->
                        if (reminder.takeStatus == TakeStatus.Pending) {
                            viewModelScope.launch {
                                val reminderEntity = ReminderEntity(
                                    id = reminder.reminderId,
                                    pill_id = reminder.drugId,
                                    time = reminder.time,
                                    takeStatus = reminder.takeStatus
                                )
                                useCases.updateReminderUseCase(
                                    reminder = reminderEntity.copy(
                                        takeStatus = TakeStatus.Missed
                                    )
                                )
                            }
                        }
                    }
            }.launchIn(viewModelScope)

    }

    private fun getReminderListForDay() {
        useCases.getDrugWithReminderListUseCase()
            .onEach { listOfReminders ->
                _state.update {
                    it.copy(
                        reminderList = listOfReminders.filter { it.time in state.value.selectedDay..state.value.selectedDay + 84000000 }
                            .sortedBy { it.time }
                    )
                }
                Log.d("D", listOfReminders.toString())
            }.launchIn(viewModelScope)
    }

    private fun setCalendarToStartOfDay() {
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        Log.d("Calendar", calendar.time.toString())
    }

    private fun daysList() {
        setCalendarToStartOfDay()
        calendar.add(Calendar.DAY_OF_YEAR, -2)
        _listOfDays.add(calendar.timeInMillis)
        for (i in 0..14) {
            calendar.add(Calendar.DAY_OF_YEAR, 1)
            _listOfDays.add(calendar.timeInMillis)
        }
    }


}