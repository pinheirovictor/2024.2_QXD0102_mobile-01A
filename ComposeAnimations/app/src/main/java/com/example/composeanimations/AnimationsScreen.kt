package com.example.composeanimations

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun AnimationsShowcaseScreen() {
    // Estados para animações
    var visible by remember { mutableStateOf(true) }
    var scale by remember { mutableStateOf(1f) }
    var rotation by remember { mutableStateOf(0f) }
    var offsetX by remember { mutableStateOf(0.dp) }
    var offsetY by remember { mutableStateOf(0.dp) }
    var boxColor by remember { mutableStateOf(Color.Blue) }

    // Animações
    val animatedAlpha by animateFloatAsState(if (visible) 1f else 0f)
    val animatedScale by animateFloatAsState(scale)
    val animatedRotation by animateFloatAsState(rotation)
    val animatedOffsetX by animateDpAsState(offsetX)
    val animatedOffsetY by animateDpAsState(offsetY)
    val animatedColor by animateColorAsState(targetValue = boxColor)

    // Animação infinita
    val infiniteTransition = rememberInfiniteTransition()
    val pulsatingScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        )
    )

    // UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Exemplo Completo de Animações", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Box com múltiplas animações
        Box(
            modifier = Modifier
                .size(100.dp)
                .offset(x = animatedOffsetX, y = animatedOffsetY)
                .graphicsLayer(
                    scaleX = animatedScale,
                    scaleY = animatedScale,
                    rotationZ = animatedRotation
                )
                .background(animatedColor.copy(alpha = animatedAlpha))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botões de Controle
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { visible = !visible }) { Text("Fade In/Out") }
            Button(onClick = { scale = if (scale == 1f) 1.5f else 1f }) { Text("Escala") }
            Button(onClick = { rotation += 45f }) { Text("Rotação") }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { offsetX += 50.dp }) { Text("Mover Direita") }
            Button(onClick = { offsetY += 50.dp }) { Text("Mover Baixo") }
            Button(onClick = { boxColor = if (boxColor == Color.Blue) Color.Red else Color.Blue }) {
                Text("Mudar Cor")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { visible = !visible }) {
            Text("Pulsar (Animação Infinita)")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão Resetar
        Button(onClick = {
            // Restaurar valores iniciais
            visible = true
            scale = 1f
            rotation = 0f
            offsetX = 0.dp
            offsetY = 0.dp
            boxColor = Color.Blue
        }) {
            Text("Resetar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Box para animação infinita
        Box(
            modifier = Modifier
                .size(100.dp * pulsatingScale)
                .background(Color.Green.copy(alpha = 0.5f))
        )
    }
}
