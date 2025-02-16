package br.ufc.quixada.authaula.data

import android.content.Context
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

/**
 * Classe responsável por gerenciar a autenticação dos usuários e comunicação com o Firebase.
 */
class AuthRepository {
    // Instância do Firebase Authentication para autenticação de usuários
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // Instância do Firestore para armazenamento de dados dos usuários
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    /**
     * Método para registrar um novo usuário utilizando email e senha.
     * Após a criação, os dados do usuário são armazenados no Firestore.
     *
     * @param email Email do usuário
     * @param password Senha do usuário
     * @param name Nome do usuário
     * @return Retorna `true` se o cadastro for bem-sucedido, `false` caso contrário.
     */
    suspend fun registerUser(email: String, password: String, name: String): Boolean {
        return try {
            // Cria o usuário no Firebase Authentication
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val uid = result.user?.uid

            // Se o usuário foi criado com sucesso, salva no Firestore
            if (uid != null) {
                val user = hashMapOf(
                    "uid" to uid,
                    "name" to name,
                    "email" to email,
                    "created_at" to System.currentTimeMillis()
                )
                firestore.collection("users").document(uid).set(user).await()
            }
            true
        } catch (e: Exception) {
            Log.e("AuthRepository", "Erro no cadastro: ${e.message}")
            false
        }
    }

    /**
     * Método para autenticação do usuário utilizando email e senha.
     *
     * @param email Email do usuário
     * @param password Senha do usuário
     * @return Retorna `true` se o login for bem-sucedido, `false` caso contrário.
     */
    suspend fun loginUser(email: String, password: String): Boolean {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            true
        } catch (e: Exception) {
            Log.e("AuthRepository", "Erro no login: ${e.message}")
            false
        }
    }

    /**
     * Método para solicitar redefinição de senha para um usuário que esqueceu sua senha.
     * O Firebase envia um email com um link para redefinição de senha.
     *
     * @param email Email do usuário para envio do link de redefinição.
     * @return Retorna `true` se o email foi enviado com sucesso, `false` caso contrário.
     */
    suspend fun resetPassword(email: String): Boolean {
        return try {
            auth.sendPasswordResetEmail(email).await()
            true
        } catch (e: Exception) {
            Log.e("AuthRepository", "Erro ao enviar email de recuperação: ${e.message}")
            false
        }
    }

    /**
     * Método para obter o nome do usuário atualmente autenticado.
     * O nome é buscado no Firestore com base no UID do usuário logado.
     *
     * @return Retorna o nome do usuário se encontrado, ou `null` se houver erro.
     */
    suspend fun getUserName(): String? {
        return try {
            val uid = auth.currentUser?.uid
            if (uid != null) {
                val snapshot = firestore.collection("users").document(uid).get().await()
                snapshot.getString("name") // Retorna o nome salvo no Firestore
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("AuthRepository", "Erro ao buscar nome do usuário: ${e.message}")
            null
        }
    }

    /**
     * Método para obter o cliente de autenticação do Google.
     * Esse cliente será usado para iniciar o fluxo de login com a conta Google.
     *
     * @param context Contexto da aplicação
     * @return Retorna um objeto `GoogleSignInClient` configurado para o login.
     */
    fun getGoogleSignInClient(context: Context): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(br.ufc.quixada.authaula.R.string.default_web_client_id)) // Token do cliente configurado no Firebase
            .requestEmail() // Solicita o email do usuário
            .build()
        return GoogleSignIn.getClient(context, gso)
    }

    /**
     * Método para autenticar um usuário utilizando sua conta do Google.
     * O ID Token obtido no login do Google é utilizado para criar credenciais no Firebase.
     * Se o usuário não existir no Firestore, ele é cadastrado automaticamente.
     *
     * @param idToken Token de autenticação do Google
     * @return Retorna `true` se o login for bem-sucedido, `false` caso contrário.
     */
    suspend fun loginWithGoogle(idToken: String): Boolean {
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val result = auth.signInWithCredential(credential).await()
            val user = result.user

            user?.let {
                val uid = it.uid
                val name = it.displayName ?: "Usuário"
                val email = it.email ?: ""

                // Verifica se o usuário já existe no Firestore antes de salvar
                val userRef = firestore.collection("users").document(uid)
                val snapshot = userRef.get().await()

                if (!snapshot.exists()) {
                    val userData = hashMapOf(
                        "uid" to uid,
                        "name" to name,
                        "email" to email,
                        "created_at" to System.currentTimeMillis()
                    )
                    userRef.set(userData).await()
                }
            }
            true
        } catch (e: Exception) {
            Log.e("AuthRepository", "Erro no login Google: ${e.message}")
            false
        }
    }

    /**
     * Método para realizar logout do usuário.
     * Após o logout, o usuário precisará fazer login novamente para acessar a aplicação.
     */
    fun logout() {
        auth.signOut()
    }

    /**
     * Método para verificar se há um usuário autenticado no Firebase.
     *
     * @return Retorna `true` se houver um usuário autenticado, `false` caso contrário.
     */
    fun isUserLogged(): Boolean {
        return auth.currentUser != null
    }
}






//package br.ufc.quixada.authaula.data
//
//import android.content.Context
//import android.util.Log
//import com.google.android.gms.auth.api.signin.GoogleSignIn
//import com.google.android.gms.auth.api.signin.GoogleSignInClient
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.GoogleAuthProvider
//import com.google.firebase.firestore.FirebaseFirestore
//import kotlinx.coroutines.tasks.await
//
//class AuthRepository {
//    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
//    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
//
//    // Registro de usuário
//    suspend fun registerUser(email: String, password: String, name: String): Boolean {
//        return try {
//            val result = auth.createUserWithEmailAndPassword(email, password).await()
//            val uid = result.user?.uid
//
//            if (uid != null) {
//                val user = hashMapOf(
//                    "uid" to uid,
//                    "name" to name,
//                    "email" to email,
//                    "created_at" to System.currentTimeMillis()
//                )
//                firestore.collection("users").document(uid).set(user).await()
//            }
//            true
//        } catch (e: Exception) {
//            Log.e("AuthRepository", "Erro no cadastro: ${e.message}")
//            false
//        }
//    }
//
//
//
//    // Login com email e senha
//    suspend fun loginUser(email: String, password: String): Boolean {
//        return try {
//            auth.signInWithEmailAndPassword(email, password).await()
//            true
//        } catch (e: Exception) {
//            Log.e("AuthRepository", "Erro no login: ${e.message}")
//            false
//        }
//    }
//
//    suspend fun resetPassword(email: String): Boolean {
//        return try {
//            auth.sendPasswordResetEmail(email).await()
//            true
//        } catch (e: Exception) {
//            Log.e("AuthRepository", "Erro ao enviar email de recuperação: ${e.message}")
//            false
//        }
//    }
//
//    suspend fun getUserName(): String? {
//        return try {
//            val uid = auth.currentUser?.uid
//            if (uid != null) {
//                val snapshot = firestore.collection("users").document(uid).get().await()
//                snapshot.getString("name") // Retorna o nome salvo no Firestore
//            } else {
//                null
//            }
//        } catch (e: Exception) {
//            Log.e("AuthRepository", "Erro ao buscar nome do usuário: ${e.message}")
//            null
//        }
//    }
//
//
//
//
//
//
//
//    // Login com Google
//    fun getGoogleSignInClient(context: Context): GoogleSignInClient {
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(context.getString(br.ufc.quixada.authaula.R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//        return GoogleSignIn.getClient(context, gso)
//    }
//
//    suspend fun loginWithGoogle(idToken: String): Boolean {
//        return try {
//            val credential = GoogleAuthProvider.getCredential(idToken, null)
//            val result = auth.signInWithCredential(credential).await()
//            val user = result.user
//
//            user?.let {
//                val uid = it.uid
//                val name = it.displayName ?: "Usuário"
//                val email = it.email ?: ""
//
//                // Verifica se o usuário já existe no Firestore antes de salvar
//                val userRef = firestore.collection("users").document(uid)
//                val snapshot = userRef.get().await()
//
//                if (!snapshot.exists()) {
//                    val userData = hashMapOf(
//                        "uid" to uid,
//                        "name" to name,
//                        "email" to email,
//                        "created_at" to System.currentTimeMillis()
//                    )
//                    userRef.set(userData).await()
//                }
//            }
//            true
//        } catch (e: Exception) {
//            Log.e("AuthRepository", "Erro no login Google: ${e.message}")
//            false
//        }
//    }
//
//
//    // Logout
//    fun logout() {
//        auth.signOut()
//    }
//
//    // Verifica se o usuário está logado
//    fun isUserLogged(): Boolean {
//        return auth.currentUser != null
//    }
//}
