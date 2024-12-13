package com.example.materialdesign.ui.screens

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.util.Calendar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

//@Composable
//fun DatePickerExample() {
//    val context = LocalContext.current
//    val calendar = Calendar.getInstance()
//    val year = calendar.get(Calendar.YEAR)
//    val month = calendar.get(Calendar.MONTH)
//    val day = calendar.get(Calendar.DAY_OF_MONTH)
//
//    var selectedDate by remember { mutableStateOf("") }
//
//    Button(onClick = {
//        DatePickerDialog(
//            context,
//            { _, selectedYear, selectedMonth, selectedDay ->
//                selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
//            },
//            year,
//            month,
//            day
//        ).show()
//    }) {
//        Text("Select Date")
//    }
//
//    if (selectedDate.isNotEmpty()) {
//        Text("Selected Date: $selectedDate", modifier = Modifier.padding(16.dp))
//    }
//}

@Composable
fun DatePickerExample2() {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    var selectedDate by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = {
            // Mostra o DatePickerDialog ao clicar
            DatePickerDialog(
                context,
                { _, selectedYear, selectedMonth, selectedDay ->
                    selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                },
                year,
                month,
                day
            ).show()
        }) {
            Text("Select Date")
        }

        if (selectedDate.isNotEmpty()) {
            Text("Selected Date: $selectedDate", modifier = Modifier.padding(16.dp))
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DatePickerPreview() {
//    // Versão mock para o preview
//    var selectedDate by remember { mutableStateOf("01/01/2024") }
//
//    Column(modifier = Modifier.padding(16.dp)) {
//        Button(onClick = { /* Simula uma interação no Preview */ }) {
//            Text("Select Date")
//        }
//
//        Text("Selected Date: $selectedDate", modifier = Modifier.padding(16.dp))
//    }
//}


@Composable
fun SwitchExample() {
    var isChecked by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp)
    ) {
        Switch(
            checked = isChecked,
            onCheckedChange = { isChecked = it }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(if (isChecked) "Switch is ON" else "Switch is OFF")
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TimePickerExample() {
    Scaffold {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val context = LocalContext.current

            var selectedTime by remember { mutableStateOf("") }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = {
                    TimePickerDialog(
                        context,
                        { _, hourOfDay, minute ->
                            selectedTime = "%02d:%02d".format(hourOfDay, minute)
                        },
                        12,
                        0,
                        true
                    ).show()
                }) {
                    Text("Select Time")
                }

                if (selectedTime.isNotEmpty()) {
                    Text(
                        text = "Selected Time: $selectedTime",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CircularProgressIndicatorExample() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun LinearProgressIndicatorExample() {
    var progress by remember { mutableStateOf(0.3f) }

    Column(modifier = Modifier.padding(16.dp)) {
        LinearProgressIndicator(progress = progress)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { progress += 0.1f }) {
            Text("Increase Progress")
        }
    }
}


@Composable
@Preview
fun prevComponents2(){
    CircularProgressIndicatorExample()
}