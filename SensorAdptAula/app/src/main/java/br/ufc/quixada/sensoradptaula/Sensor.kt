package br.ufc.quixada.sensoradptaula

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SensorScreen(sensorManager: SensorManager) {
    var accelerometerData by remember { mutableStateOf(Triple(0f, 0f, 0f)) }
    var gyroscopeData by remember { mutableStateOf(Triple(0f, 0f, 0f)) }
    var isCapturing by remember { mutableStateOf(false) }

    LaunchedEffect(isCapturing) {
        if (isCapturing) {
            val sensorListener = object : SensorEventListener {
                override fun onSensorChanged(event: SensorEvent?) {
                    event?.let {
                        when (event.sensor.type) {
                            Sensor.TYPE_ACCELEROMETER -> {
                                accelerometerData = Triple(event.values[0], event.values[1], event.values[2])
                            }
                            Sensor.TYPE_GYROSCOPE -> {
                                gyroscopeData = Triple(event.values[0], event.values[1], event.values[2])
                            }
                        }
                    }
                }

                override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
            }

            val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            val gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

            accelerometer?.let { sensorManager.registerListener(sensorListener, it, SensorManager.SENSOR_DELAY_UI) }
            gyroscope?.let { sensorManager.registerListener(sensorListener, it, SensorManager.SENSOR_DELAY_UI) }

            // Aguarda 500ms para capturar os valores
            delay(500)

            sensorManager.unregisterListener(sensorListener)
            isCapturing = false
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Dados dos Sensores", fontSize = 24.sp, modifier = Modifier.padding(8.dp))

        SensorDataDisplay(title = "Acelerômetro", data = accelerometerData)
        Spacer(modifier = Modifier.height(16.dp))
        SensorDataDisplay(title = "Giroscópio", data = gyroscopeData)

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { isCapturing = true }) {
            Text(text = if (isCapturing) "Capturando..." else "Capturar Sensores")
        }
    }
}

@Composable
fun SensorDataDisplay(title: String, data: Triple<Float, Float, Float>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp))
            Text(text = "X: ${data.first}")
            Text(text = "Y: ${data.second}")
            Text(text = "Z: ${data.third}")
        }
    }
}