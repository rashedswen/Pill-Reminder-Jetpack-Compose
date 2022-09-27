package com.kwemrj.pillreminder.presentation.add_reminder.component

import android.text.format.DateFormat
import android.util.Log
import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

@Composable
fun DateSelectSection(
    modifier: Modifier = Modifier,
    onDateStartChange: (Long) -> Unit,
    onDateEndChange: (Long) -> Unit,
    startDateValue: Long,
    endDateValue: Long,
) {

    var startDateShowPicker by remember { mutableStateOf(false) }
    var endDateShowPicker by remember { mutableStateOf(false) }

    if (startDateShowPicker) {
        CalendarDialog(
            onDateChange = onDateStartChange,
            onDismissRequest = {
                startDateShowPicker = false
            },
            selectedDate = startDateValue
        )
    }

    if (endDateShowPicker) {
        CalendarDialog(
            onDateChange = onDateEndChange,
            onDismissRequest = {
                endDateShowPicker = false
            },
            selectedDate = endDateValue,
            startDate = startDateValue
        )
    }


    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        CustomDatePickerField(
            text = "Start Date",
            modifier = Modifier
                .weight(1f),
            onValueChange = {},
            timestamp = startDateValue,
            onOpenDatePicker = {
                startDateShowPicker = true
            }
        )

        Spacer(modifier = Modifier.width(32.dp))

        CustomDatePickerField(
            text = "End Date",
            modifier = Modifier.weight(1f),
            onValueChange = {},
            timestamp = endDateValue,
            onOpenDatePicker = {
                endDateShowPicker = true
            }
        )
    }

}

@Composable
fun CalendarDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onDateChange: (Long) -> Unit,
    selectedDate: Long? = 0L,
    startDate: Long? = null,
) {

    var selDate by remember { mutableStateOf(Calendar.getInstance().timeInMillis) }

    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(dismissOnBackPress = true)
    ) {
        Column(
            modifier = modifier
                .wrapContentSize()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .defaultMinSize(minHeight = 72.dp)
                    .fillMaxWidth()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
                    .padding(16.dp)
            ) {

                Text(
                    text = "Select Date",
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.onBackground
                )
                Spacer(modifier = Modifier.size(24.dp))
                Text(
                    text = DateFormat.format("MMM d,yyyy", selectedDate ?: 0L).toString(),
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.size(8.dp))
            }
            CustomCalendarView(onDateChange = {
                selDate = it
                onDateChange(selDate)
            }, startDate = startDate)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                OutlinedButton(onClick = {
                    onDismissRequest()
                }) {
                    Text(text = "Cancel")
                }
                Spacer(modifier = Modifier.size(8.dp))
                Button(onClick = {
                    onDateChange(selDate)
                    onDismissRequest()
                }) {
                    Text(text = "OK")
                }
            }
        }
    }
}


@Composable
fun CustomCalendarView(onDateChange: (Long) -> Unit, startDate: Long?) {
    AndroidView(
        modifier = Modifier.wrapContentSize(),
        factory = { context ->
            CalendarView(context)
        },
        update = { view ->
            if (startDate != null) {
                view.minDate = startDate
            }
            view.setOnDateChangeListener { _, year, month, dayOfMonth ->
                val calendar = Calendar.getInstance()
                calendar.set(year, month, dayOfMonth)
                onDateChange(calendar.timeInMillis)
            }
        }
    )
}

@Composable
fun CustomDatePickerField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (Long) -> Unit,
    timestamp: Long,
    onOpenDatePicker: () -> Unit
) {
    Log.d("sssss", "$timestamp")
    OutlinedTextField(
        modifier = modifier
            .clickable { onOpenDatePicker() },
        label = {
            Text(text = text, color = Color.Black)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            backgroundColor = Color.Transparent,
            disabledTextColor = Color.Black,
            disabledTrailingIconColor = Color.Black
        ),
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = null,
                modifier = Modifier.clickable { onOpenDatePicker() })
        },
        value = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(timestamp),
        onValueChange = {
            onValueChange(it.toLong())
        },
        shape = RoundedCornerShape(16.dp),
        readOnly = false,
        enabled = false
    )

}


fun extractNumber( num: Int,  nums: MutableList<Int>):MutableList<Int> {
    // write your code here
    val listOfNumbersModified = mutableListOf<Int>()
    val listOfNotExactNumbers = mutableListOf<Int>()
    val listOfExactNumbers = mutableListOf<Int>()

    nums.forEach {
        if(it != num){
            listOfNotExactNumbers.add(it)
        }
        if(it == num){
            listOfExactNumbers.add(it)
        }
    }
    listOfNumbersModified.addAll(listOfNotExactNumbers)
    listOfNumbersModified.addAll(listOfExactNumbers)
    return listOfNumbersModified
}


fun main() {
    print(extractNumber(2,mutableListOf<Int>(2,4,2)))
}

