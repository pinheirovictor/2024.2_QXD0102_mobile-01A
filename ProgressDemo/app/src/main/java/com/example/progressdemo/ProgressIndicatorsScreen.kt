package com.example.progressdemo

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

@Composable
fun ProgressIndicatorsScreen() {
    // Estado do progresso (de 0.0f a 1.0f)
    var progress by remember { mutableStateOf(0.0f) }

    // UI do Jetpack Compose
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Exemplo de Progress Indicators", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(20.dp))

        // Circular Progress Indicator
        CircularProgressIndicator(
            modifier = Modifier.size(64.dp),
            color = Color.Red,
            strokeWidth = 6.dp
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Linear Progress Indicator
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            color = Color.Yellow
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Botão para aumentar o progresso
        Button(
            onClick = {
                if (progress < 1.0f) {
                    progress += 0.1f // Incrementa o progresso
                }
            }
        ) {
            Text("Aumentar Progresso")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Exibir progresso atual como texto
        Text("Progresso: ${(progress * 100).toInt()}%", style = MaterialTheme.typography.bodyLarge)
    }
}