package com.example.crudapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Atividade principal que inicializa o ViewModel e define o conteúdo da UI
class MainActivity : ComponentActivity() {
    private val viewModel: UserViewModel by viewModels() // Cria uma instância do ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { UserApp(viewModel) } // Define o conteúdo com a função composable
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter") // Supressão de warning relacionado ao Scaffold
@Composable
fun UserApp(viewModel: UserViewModel) {
    val usuarios by viewModel.users.observeAsState(emptyList()) // Observa a lista de usuários
    var nome by remember { mutableStateOf("") } // Estado para o nome do usuário
    var idade by remember { mutableStateOf("") } // Estado para a idade do usuário
    var usuarioEditando by remember { mutableStateOf<User?>(null) } // Estado para usuário em edição
    var mensagemErro by remember { mutableStateOf<String?>(null) } // Estado para mensagem de erro

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gerenciamento de Usuários") },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White
            )
        }
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

            // Cabeçalho do formulário
            Text(
                if (usuarioEditando == null) "Adicionar um Novo Usuário" else "Editar Usuário",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Campo para inserir o nome do usuário
            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Campo para inserir a idade do usuário
            OutlinedTextField(
                value = idade,
                onValueChange = { idade = it },
                label = { Text("Idade") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botão para adicionar ou salvar alterações
            Button(
                onClick = {
                    try {
                        if (nome.isNotEmpty() && idade.isNotEmpty()) {
                            val idadeInt = idade.toInt()
                            if (usuarioEditando == null) {
                                // Adiciona novo usuário
                                viewModel.addUser(User(name = nome, age = idadeInt))
                            } else {
                                // Atualiza usuário existente
                                viewModel.updateUser(usuarioEditando!!.copy(name = nome, age = idadeInt))
                                usuarioEditando = null
                            }
                            nome = ""
                            idade = ""
                            mensagemErro = null
                        }
                    } catch (e: NumberFormatException) {
                        mensagemErro = "A idade deve ser um número válido" // Mensagem de erro para idade inválida
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(if (usuarioEditando == null) "Adicionar Usuário" else "Salvar Alterações")
            }

            // Exibe a mensagem de erro, se houver
            mensagemErro?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(it, color = Color.Red, modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = Color.Gray, thickness = 1.dp)

            // Lista de usuários
            Text(
                "Lista de Usuários",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            LazyColumn {
                items(usuarios) { usuario ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        elevation = 4.dp
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(usuario.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                                Text("Idade: ${usuario.age}", color = Color.Gray, fontSize = 14.sp)
                            }
                            // Botão para editar o usuário
                            IconButton(onClick = {
                                nome = usuario.name
                                idade = usuario.age.toString()
                                usuarioEditando = usuario
                            }) {
                                Icon(Icons.Default.Edit, contentDescription = "Editar Usuário", tint = Color.Blue)
                            }
                            // Botão para excluir o usuário
                            IconButton(onClick = { viewModel.deleteUser(usuario) }) {
                                Icon(Icons.Default.Delete, contentDescription = "Excluir Usuário", tint = Color.Red)
                            }
                        }
                    }
                }
            }
        }
    }
}


//Scaffold: Componente que organiza a estrutura geral, com uma barra superior (TopAppBar) e um conteúdo principal.
//Formulário de entrada: Contém campos para nome e idade do usuário, além de um botão para adicionar ou salvar.
//Lista de usuários: Utiliza LazyColumn para exibir a lista de usuários em cartões, cada um com botões para editar e excluir.
//Estados (remember e LiveData): Gerenciam os dados em tempo real e garantem que a UI se atualize automaticamente ao modificar a lista.
















//package com.example.crudapp
//
//import android.annotation.SuppressLint
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.viewModels
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Delete
//import androidx.compose.material.icons.filled.Edit
//import androidx.compose.runtime.*
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
//class MainActivity : ComponentActivity() {
//    private val viewModel: UserViewModel by viewModels()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent { UserApp(viewModel) }
//    }
//}
//
//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun UserApp(viewModel: UserViewModel) {
//    val usuarios by viewModel.users.observeAsState(emptyList())
//    var nome by remember { mutableStateOf("") }
//    var idade by remember { mutableStateOf("") }
//    var usuarioEditando by remember { mutableStateOf<User?>(null) } // Armazena o usuário sendo editado
//    var mensagemErro by remember { mutableStateOf<String?>(null) }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Gerenciamento de Usuários") },
//                backgroundColor = MaterialTheme.colors.primary,
//                contentColor = Color.White
//            )
//        }
//    ) {
//        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
//            // Formulário de entrada
//            Text(
//                if (usuarioEditando == null) "Adicionar um Novo Usuário" else "Editar Usuário",
//                fontWeight = FontWeight.Bold,
//                fontSize = 20.sp,
//                modifier = Modifier.padding(bottom = 16.dp)
//            )
//
//            OutlinedTextField(
//                value = nome,
//                onValueChange = { nome = it },
//                label = { Text("Nome") },
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            OutlinedTextField(
//                value = idade,
//                onValueChange = { idade = it },
//                label = { Text("Idade") },
//                modifier = Modifier.fillMaxWidth(),
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Button(
//                onClick = {
//                    try {
//                        if (nome.isNotEmpty() && idade.isNotEmpty()) {
//                            val idadeInt = idade.toInt()
//                            if (usuarioEditando == null) {
//                                // Adicionar novo usuário
//                                viewModel.addUser(User(name = nome, age = idadeInt))
//                            } else {
//                                // Atualizar usuário existente
//                                viewModel.updateUser(usuarioEditando!!.copy(name = nome, age = idadeInt))
//                                usuarioEditando = null
//                            }
//                            nome = ""
//                            idade = ""
//                            mensagemErro = null
//                        }
//                    } catch (e: NumberFormatException) {
//                        mensagemErro = "A idade deve ser um número válido"
//                    }
//                },
//                modifier = Modifier.align(Alignment.CenterHorizontally)
//            ) {
//                Text(if (usuarioEditando == null) "Adicionar Usuário" else "Salvar Alterações")
//            }
//
//            // Exibe mensagem de erro, se houver
//            mensagemErro?.let {
//                Spacer(modifier = Modifier.height(8.dp))
//                Text(it, color = Color.Red, modifier = Modifier.align(Alignment.CenterHorizontally))
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Divider(color = Color.Gray, thickness = 1.dp)
//
//            // Lista de usuários
//            Text(
//                "Lista de Usuários",
//                fontWeight = FontWeight.Bold,
//                fontSize = 20.sp,
//                modifier = Modifier.padding(vertical = 16.dp)
//            )
//
//            LazyColumn {
//                items(usuarios) { usuario ->
//                    Card(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(vertical = 4.dp),
//                        elevation = 4.dp
//                    ) {
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(16.dp),
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            Column(modifier = Modifier.weight(1f)) {
//                                Text(usuario.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
//                                Text("Idade: ${usuario.age}", color = Color.Gray, fontSize = 14.sp)
//                            }
//                            IconButton(onClick = {
//                                // Inicia a edição
//                                nome = usuario.name
//                                idade = usuario.age.toString()
//                                usuarioEditando = usuario
//                            }) {
//                                Icon(Icons.Default.Edit, contentDescription = "Editar Usuário", tint = Color.Blue)
//                            }
//                            IconButton(onClick = { viewModel.deleteUser(usuario) }) {
//                                Icon(Icons.Default.Delete, contentDescription = "Excluir Usuário", tint = Color.Red)
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
