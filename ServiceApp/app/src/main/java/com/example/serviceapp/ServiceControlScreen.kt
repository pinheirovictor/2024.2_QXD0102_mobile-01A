
package com.example.serviceapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Composable que define a interface de controle do serviço.
 * Apresenta botões para iniciar e parar o serviço em segundo plano.
 *
 * @param onStartClick Callback para ser executado ao clicar no botão "Iniciar Serviço".
 * @param onStopClick Callback para ser executado ao clicar no botão "Parar Serviço".
 */
@Composable
fun ServiceControlScreen(
    onStartClick: () -> Unit, // Função chamada ao clicar no botão de iniciar
    onStopClick: () -> Unit // Função chamada ao clicar no botão de parar
) {
    // Define um contêiner que preenche toda a tela e centralizar o conteúdo
    Box(
        contentAlignment = Alignment.Center, // Alinha o conteúdo no centro
        modifier = Modifier.fillMaxSize() // Faz o Box ocupar todo o tamanho da tela
    ) {
        // Coluna que organiza os botões verticalmente
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, // Alinha os itens horizontalmente ao centro
            verticalArrangement = Arrangement.Center // Espaça os itens uniformemente ao centro
        ) {
            // Botão para iniciar o serviço
            Button(
                onClick = onStartClick, // Callback para iniciar o serviço
                modifier = Modifier.padding(16.dp) // Adiciona espaçamento ao redor do botão
            ) {
                Text(text = "Iniciar Serviço") // Texto exibido no botão
            }
            // Botão para parar o serviço
            Button(
                onClick = onStopClick, // Callback para parar o serviço
                modifier = Modifier.padding(16.dp) // Adiciona espaçamento ao redor do botão
            ) {
                Text(text = "Parar Serviço") // Texto exibido no botão
            }
        }
    }
}

@Composable
@Preview
fun ver(){
    ServiceControlScreen(
        onStartClick = { /* Implementação vazia para Preview */ },
        onStopClick = { /* Implementação vazia para Preview */ }
    )
}


//package com.example.serviceapp
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//
//@Composable
//fun ServiceControlScreen(
//    onStartClick: () -> Unit,
//    onStopClick: () -> Unit
//) {
//    Box(
//        contentAlignment = Alignment.Center,
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Button(
//                onClick = onStartClick,
//                modifier = Modifier.padding(16.dp)
//            ) {
//                Text(text = "Iniciar Serviço")
//            }
//            Button(
//                onClick = onStopClick,
//                modifier = Modifier.padding(16.dp)
//            ) {
//                Text(text = "Parar Serviço")
//            }
//        }
//    }
//}
