package br.ufc.quixada.sensoradptaula

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


@Composable
fun ContextAwareScreen() {
    var isDarkTheme by remember { mutableStateOf(getCurrentTheme()) }
    val context = LocalContext.current

    // Atualiza o tema a cada minuto para acompanhar mudanças de horário
    LaunchedEffect(Unit) {
        while (true) {
            isDarkTheme = getCurrentTheme()
            delay(60000L) // Atualiza a cada 60 segundos
        }
    }

    // Define as cores de acordo com o tema
    val backgroundColor = if (isDarkTheme) Color.Black else Color.White
    val textColor = if (isDarkTheme) Color.White else Color.Black

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (isDarkTheme) "Modo Escuro Ativado" else "Modo Claro Ativado",
                color = textColor,
                style = MaterialTheme.typography.bodyLarge // Estilo recomendado no Material3
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { checkNetworkType(context) }) {
                Text(text = "Verificar Tipo de Rede")
            }
        }
    }
}
