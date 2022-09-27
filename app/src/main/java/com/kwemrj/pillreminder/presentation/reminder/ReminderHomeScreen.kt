package com.kwemrj.pillreminder.presentation.reminder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kwemrj.pillreminder.core.nav_graph.ADD_EDIT_ROUTE
import com.kwemrj.pillreminder.data.local.entity.ReminderWithMedication
import com.kwemrj.pillreminder.presentation.reminder.component.DaySectionCard
import com.kwemrj.pillreminder.presentation.reminder.component.ReminderDialog
import com.kwemrj.pillreminder.presentation.reminder.component.ReminderItems
import com.kwemrj.pillreminder.ui.theme.Purple700


@Composable
fun ReminderHomeScreen(
    navController: NavController,
    viewModel: ReminderHomeViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()

    val state = viewModel.state.collectAsState()
    val listOfDays = viewModel.listOfDays


    var showMedicationDialog by remember { mutableStateOf(false) }
    var medicationDialogItem by remember { mutableStateOf<ReminderWithMedication?>(null) }

    if (showMedicationDialog) {
        if (medicationDialogItem != null)
            ReminderDialog(
                modifier = Modifier.fillMaxWidth(),
                reminder = medicationDialogItem!!,
                onSkipRequest = {
                    viewModel.onEvent(ReminderHomeEvents.OnSkipRequest(medicationDialogItem!!))
                },
                onTakeRequest = {
                    viewModel.onEvent(ReminderHomeEvents.OnTakeRequest(medicationDialogItem!!))

                },
                onDismissRequest =
                {
                    showMedicationDialog = false
                    medicationDialogItem = null
                })
    }

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(ADD_EDIT_ROUTE)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
    ) {
        Column(
            modifier = Modifier
                .background(Color(0x12B0E1E7))
                .padding(it)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Purple700)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Pill Reminder", fontSize = 22.sp, color = Color.White)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp, start = 8.dp)
            ) {
                DaySectionCard(
                    modifier = Modifier.fillMaxWidth(),
                    onDayChange = {
                        viewModel.onEvent(ReminderHomeEvents.OnDayChange(it))
                    },
                    selectedDay = state.value.selectedDay,
                    listOfDays = listOfDays
                )
            }

            if (state.value.reminderList.isNotEmpty())
                ReminderItems(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    remindersList = state.value.reminderList,
                    selectedReminder = { reminder ->
                        showMedicationDialog = true
                        medicationDialogItem = reminder
                        viewModel.onEvent(ReminderHomeEvents.ReminderDetails(reminder))
                    }
                )
            else
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(text = "No Medication Today", style = MaterialTheme.typography.h5)

                }
        }
    }
}
