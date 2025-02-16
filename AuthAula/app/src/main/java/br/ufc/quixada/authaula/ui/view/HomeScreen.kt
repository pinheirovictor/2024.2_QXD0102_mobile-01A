package br.ufc.quixada.authaula.ui.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.ufc.quixada.authaula.viewmodel.AuthViewModel

@Composable
fun HomeScreen(viewModel: AuthViewModel, navController: NavController) {
    var userName by remember { mutableStateOf("Carregando...") }
    var isVisible by remember { mutableStateOf(false) }

    // Buscar nome do usuário no Firestore
    LaunchedEffect(Unit) {
        viewModel.getUserName { name ->
            userName = name ?: "Usuário"
        }
        isVisible = true // Inicia a animação
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AnimatedVisibility(
            visible = isVisible,
            enter = slideInVertically(initialOffsetY = { -50 }) + fadeIn()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Texto de boas-vindas estilizado
                Text(
                    text = "Bem-vindo, $userName!",
                    fontSize = 28.sp,
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // Botão de Logout
                Button(
                    onClick = {
                        viewModel.logout()
                        navController.navigate("login") // Volta para a tela de login
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Sair", fontSize = 18.sp, color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
    }
}





//package br.ufc.quixada.authaula.ui.view
//
//import android.widget.Toast
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import br.ufc.quixada.authaula.viewmodel.AuthViewModel
//import androidx.compose.ui.unit.sp
//
//@Composable
//fun HomeScreen(viewModel: AuthViewModel, navController: NavController) {
//    var userName by remember { mutableStateOf("Carregando...") }
//
//    // Buscar nome do usuário no Firestore
//    LaunchedEffect(Unit) {
//        viewModel.getUserName { name ->
//            userName = name ?: "Usuário"
//        }
//    }
//
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text("Bem-vindo, $userName!", fontSize = (24.sp))
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//        Button(onClick = {
//            viewModel.logout()
//            navController.navigate("login") // Volta para a tela de login após logout
//        }) {
//            Text("Sair")
//        }
//    }
//}
