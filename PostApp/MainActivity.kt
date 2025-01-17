package com.example.retrofitaula

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.example.retrofitaula.ui.screens.PostScreen // Tela de posts
import com.example.retrofitaula.ui.screens.UserScreen // Tela de usuários

// Classe principal da atividade
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen() // Configura a tela principal da aplicação
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter") // Suprime aviso sobre padding padrão não utilizado
@Composable
fun MainScreen() {
    // Estado para controlar qual aba está selecionada (0 para "Usuários", 1 para "Posts")
    var selectedTab by remember { mutableStateOf(0) }

    // Scaffold é um layout que fornece uma estrutura básica para a tela, com barra superior e inferior
    Scaffold(
        // Barra superior com título
        topBar = {
            TopAppBar(
                title = { Text("PostAPP") }, // Título exibido na barra superior
                backgroundColor = MaterialTheme.colors.primary, // Cor de fundo
                contentColor = Color.White // Cor do texto e ícones
            )
        },
        // Barra de navegação inferior
        bottomBar = {
            BottomNavigation {
                // Item de navegação para a tela de usuários
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Usuários") }, // Ícone de pessoa
                    label = { Text("Usuários") }, // Rótulo "Usuários"
                    selected = selectedTab == 0, // Define se este item está selecionado
                    onClick = { selectedTab = 0 } // Altera o estado para a aba de "Usuários"
                )
                // Item de navegação para a tela de posts
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.List, contentDescription = "Posts") }, // Ícone de lista
                    label = { Text("Posts") }, // Rótulo "Posts"
                    selected = selectedTab == 1, // Define se este item está selecionado
                    onClick = { selectedTab = 1 } // Altera o estado para a aba de "Posts"
                )
            }
        }
    ) {
        // Exibe a tela correspondente com base na aba selecionada
        when (selectedTab) {
            0 -> UserScreen() // Tela de usuários
            1 -> PostScreen() // Tela de posts
        }
    }
}

