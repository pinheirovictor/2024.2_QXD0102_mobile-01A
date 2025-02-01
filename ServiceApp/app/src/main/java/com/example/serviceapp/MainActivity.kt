
package com.example.serviceapp

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.serviceapp.service.BackgroundService

/**
 * `MainActivity` é a atividade principal que controla o início e a interrupção do serviço em segundo plano.
 */
class MainActivity : ComponentActivity() {

    private val controlChannelId = "service_control_channel" // ID do canal de notificação para controle do serviço

    /**
     * Método chamado quando a atividade é criada.
     * Inicializa o canal de notificações, solicita permissões e configura a interface do usuário.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createControlNotificationChannel() // Cria o canal de notificações para controle
        requestNotificationPermission() // Solicita permissão para exibir notificações, se necessário

        // Define o conteúdo da interface do usuário com Jetpack Compose
        setContent {
            ServiceControlScreen(
                onStartClick = { startBackgroundService() }, // Ação ao clicar para iniciar o serviço
                onStopClick = { stopBackgroundService() } // Ação ao clicar para parar o serviço
            )
        }
    }

    /**
     * Inicia o serviço em segundo plano (`BackgroundService`).
     */
    private fun startBackgroundService() {
        val intent = Intent(this, BackgroundService::class.java) // Cria uma intent para o serviço
        startService(intent) // Inicia o serviço
        Toast.makeText(this, "Serviço Iniciado pelo Botão", Toast.LENGTH_SHORT).show() // Feedback para o usuário
        showControlNotification("Serviço Iniciado") // Exibe uma notificação indicando que o serviço foi iniciado
    }

    /**
     * Interrompe o serviço em segundo plano (`BackgroundService`).
     */
    private fun stopBackgroundService() {
        val intent = Intent(this, BackgroundService::class.java) // Cria uma intent para o serviço
        stopService(intent) // Para o serviço
        Toast.makeText(this, "Solicitação para Parar o Serviço", Toast.LENGTH_SHORT).show() // Feedback para o usuário
        showControlNotification("Serviço Parado") // Exibe uma notificação indicando que o serviço foi parado
    }

    /**
     * Exibe uma notificação indicando o estado atual do serviço.
     * @param message Mensagem a ser exibida na notificação.
     */
    @SuppressLint("MissingPermission")
    private fun showControlNotification(message: String) {
        val notification = NotificationCompat.Builder(this, controlChannelId) // Configura a notificação
            .setContentTitle("Controle de Serviço") // Título da notificação
            .setContentText(message) // Mensagem da notificação
            .setSmallIcon(android.R.drawable.ic_menu_info_details) // Ícone da notificação
            .setPriority(NotificationCompat.PRIORITY_HIGH) // Prioridade alta para maior visibilidade
            .build()

        // Exibe a notificação usando o NotificationManagerCompat
        NotificationManagerCompat.from(this).notify(System.currentTimeMillis().toInt(), notification)
    }

    /**
     * Cria o canal de notificação para dispositivos Android 8.0 (Oreo) ou superior.
     */
    private fun createControlNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // Verifica a versão do Android
            val channel = NotificationChannel(
                controlChannelId, // ID único do canal
                "Controle de Serviço", // Nome do canal
                NotificationManager.IMPORTANCE_HIGH // Alta prioridade para notificações importantes
            )
            val manager = getSystemService(NotificationManager::class.java) // Obtém o gerenciador de notificações
            manager?.createNotificationChannel(channel) // Cria o canal
        }
    }

    /**
     * Solicita permissão para exibir notificações em dispositivos com Android 13 (Tiramisu) ou superior.
     */
    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Verifica se a versão exige permissão para notificações
            val requestPermissionLauncher =
                registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                    if (!isGranted) { // Caso a permissão seja negada
                        Toast.makeText(this, "Permissão de Notificação Negada", Toast.LENGTH_SHORT).show()
                    }
                }
            // Lança a solicitação de permissão para notificações
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }
}


//package com.example.serviceapp
//
//import android.Manifest
//import android.annotation.SuppressLint
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.content.Context
//import android.content.Intent
//import android.os.Build
//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.result.contract.ActivityResultContracts

//import androidx.core.app.NotificationCompat
//import androidx.core.app.NotificationManagerCompat
//import com.example.serviceapp.service.BackgroundService
//
//class MainActivity : ComponentActivity() {
//
//    private val controlChannelId = "service_control_channel"
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        createControlNotificationChannel()
//        requestNotificationPermission()
//
//        setContent {
//            ServiceControlScreen(
//                onStartClick = { startBackgroundService() },
//                onStopClick = { stopBackgroundService() }
//            )
//        }
//    }
//
//    private fun startBackgroundService() {
//        val intent = Intent(this, BackgroundService::class.java)
//        startService(intent)
//        Toast.makeText(this, "Serviço Iniciado pelo Botão", Toast.LENGTH_SHORT).show()
//        showControlNotification("Serviço Iniciado")
//    }
//
//    private fun stopBackgroundService() {
//        val intent = Intent(this, BackgroundService::class.java)
//        stopService(intent)
//        Toast.makeText(this, "Solicitação para Parar o Serviço", Toast.LENGTH_SHORT).show()
//        showControlNotification("Serviço Parado")
//    }
//
//    @SuppressLint("MissingPermission")
//    private fun showControlNotification(message: String) {
//        val notification = NotificationCompat.Builder(this, controlChannelId)
//            .setContentTitle("Controle de Serviço")
//            .setContentText(message)
//            .setSmallIcon(android.R.drawable.ic_menu_info_details)
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .build()
//
//        NotificationManagerCompat.from(this).notify(System.currentTimeMillis().toInt(), notification)
//    }
//
//    private fun createControlNotificationChannel() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(
//                controlChannelId,
//                "Controle de Serviço",
//                NotificationManager.IMPORTANCE_HIGH
//            )
//            val manager = getSystemService(NotificationManager::class.java)
//            manager?.createNotificationChannel(channel)
//        }
//    }
//
//    private fun requestNotificationPermission() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            val requestPermissionLauncher =
//                registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
//                    if (!isGranted) {
//                        Toast.makeText(this, "Permissão de Notificação Negada", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
//        }
//    }
//}
