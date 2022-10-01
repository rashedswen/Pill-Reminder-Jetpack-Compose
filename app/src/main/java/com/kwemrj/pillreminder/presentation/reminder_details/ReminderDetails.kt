package com.kwemrj.pillreminder.presentation.reminder_details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kwemrj.pillreminder.core.enums.MedicationForm
import com.kwemrj.pillreminder.core.medicationFormPainter

@Composable
fun ReminderDetailsScreen(
    navController: NavController,
) {

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0x77B0E1E7))
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(modifier = Modifier.clickable {
                    navController.popBackStack()
                }, imageVector = Icons.Default.ArrowBack, contentDescription = "back")
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.NotificationsActive,
                    contentDescription = "Notification On"
                )
                Spacer(modifier = Modifier.width(12.dp))
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier
                        .background(Color(0xFCBBE8F1), shape = CircleShape)
                        .padding(8.dp)
                        .size(120.dp),
                    painter = painterResource(id = medicationFormPainter(MedicationForm.Tablet)),
                    contentDescription = "Medicine Icon"
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = "Panadol",
                    style = MaterialTheme.typography.h5
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "219", style = MaterialTheme.typography.h5)

                        Text(text = "Pill(s) taken", color = Color.Gray)
                    }
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "112", style = MaterialTheme.typography.h5)

                        Text(text = "Pill(s) in inventory", color = Color.Gray)
                    }
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "9", style = MaterialTheme.typography.h5)

                        Text(text = "Pill(s) missed", color = Color.Gray)
                    }
                }

                Divider()

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                ) {
                    Text(text = "History", style = MaterialTheme.typography.body2, color = Color(
                        0xFF53B0EE
                    )
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column() {
                            Text(text = "Fri, Aug 3, 12:00 AM, 1 pill(s) taken")
                            Spacer(modifier = Modifier.size(4.dp))
                            Text(text = "Fri, Aug 3, 12:00 AM, 1 pill(s) taken")
                            Spacer(modifier = Modifier.size(4.dp))
                            Text(text = "Fri, Aug 3, 12:00 AM, 1 pill(s) taken")
                        }
                        Spacer(modifier = Modifier.weight(1f))
//                        Icon(
//                            modifier = Modifier
//                                .size(40.dp)
//                                .background(Color(0x12B0E1E7)),
//                            imageVector = Icons.Outlined.ChevronRight,
//                            contentDescription = null,
//                        )
                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun Reminderd() {
    ReminderDetailsScreen(navController = rememberNavController())
}
