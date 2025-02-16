package br.ufc.quixada.authaula.ui.view

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.ufc.quixada.authaula.viewmodel.AuthViewModel
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.text.input.VisualTransformation
import br.ufc.quixada.authaula.R

@Composable
fun LoginScreen(viewModel: AuthViewModel, navController: NavController) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isVisible = true // Ativa animação ao entrar na tela
    }

    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            account?.idToken?.let { idToken ->
                viewModel.loginWithGoogle(idToken) { success ->
                    if (success) {
                        navController.navigate("home")
                    } else {
                        Toast.makeText(context, "Erro ao fazer login com Google", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } catch (e: ApiException) {
            Toast.makeText(context, "Erro: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AnimatedVisibility(
            visible = isVisible,
            enter = slideInVertically() + fadeIn()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Bem-vindo ao AuthApp!", fontSize = 30.sp, style = MaterialTheme.typography.headlineLarge)

                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = "Email",
                    icon = Icons.Filled.Email
                )

                CustomTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Senha",
                    icon = Icons.Filled.Lock,
                    isPassword = true
                )
                
                Spacer(modifier = Modifier.height(6.dp))

                Button(
                    onClick = {
                        viewModel.login(email, password) { success ->
                            if (success) {
                                navController.navigate("home")
                            } else {
                                Toast.makeText(context, "Usuário ou senha inválida", Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Entrar", fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        val signInIntent = viewModel.getGoogleSignInClient(context).signInIntent
                        googleSignInLauncher.launch(signInIntent)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.logo_google),
                        contentDescription = "Google Login",
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Entrar com Google", fontSize = 18.sp)
                }

                TextButton(onClick = { navController.navigate("register") }) {
                    Text("Criar Conta")
                }

                TextButton(onClick = { navController.navigate("forgotPassword") }) {
                    Text("Esqueci minha senha")
                }
            }
        }
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    isPassword: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = { Icon(icon, contentDescription = null) },
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    )
}


//package br.ufc.quixada.authaula.ui.view
//
//import android.util.Log
//import android.widget.Toast
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.material3.TextField
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.navigation.NavController
//import br.ufc.quixada.authaula.viewmodel.AuthViewModel
//import androidx.compose.ui.platform.LocalContext
//import com.google.android.gms.auth.api.signin.GoogleSignIn
//import com.google.android.gms.common.api.ApiException
//
//@Composable
//fun LoginScreen(viewModel: AuthViewModel, navController: NavController) {
//    val context = LocalContext.current
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//
//    val googleSignInLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.StartActivityForResult()
//    ) { result ->
//        Log.d("GoogleSignIn", "Resultado recebido: $result")
//
//        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
//        try {
//            val account = task.getResult(ApiException::class.java)
//            Log.d("GoogleSignIn", "Conta autenticada: ${account?.email}")
//
//            account?.idToken?.let { idToken ->
//                viewModel.loginWithGoogle(idToken) { success ->
//                    if (success) {
//                        Log.d("GoogleSignIn", "Login com Google bem-sucedido!")
//                        navController.navigate("home")
//                    } else {
//                        Log.e("GoogleSignIn", "Erro ao fazer login com Google")
//                        Toast.makeText(context, "Erro ao fazer login com Google", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        } catch (e: ApiException) {
//            Log.e("GoogleSignIn", "Erro: ${e.message}")
//            Toast.makeText(context, "Erro: ${e.message}", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
//        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
//        TextField(value = password, onValueChange = { password = it }, label = { Text("Senha") }, visualTransformation = PasswordVisualTransformation())
//
//        Button(onClick = {
//            viewModel.login(email, password) { success ->
//                if (success) {
//                    navController.navigate("home")
//                } else {
//                    Toast.makeText(context, "Usuário ou senha inválida", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }) {
//            Text("Entrar com Email/Senha")
//        }
//
//        TextButton(onClick = { navController.navigate("forgotPassword") }) {
//            Text("Esqueci minha senha")
//        }
//
//        // 🔥 Botão de Login com Google
//        Button(onClick = {
//            val signInIntent = viewModel.getGoogleSignInClient(context).signInIntent
//            googleSignInLauncher.launch(signInIntent)
//        }) {
//            Text("Entrar com Google")
//        }
//
//        TextButton(onClick = { navController.navigate("register") }) {
//            Text("Criar Conta")
//        }
//    }
//}
