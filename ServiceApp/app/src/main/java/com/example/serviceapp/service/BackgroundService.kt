package com.example.serviceapp.service

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.NotificationCompat

/**
 * Serviço em segundo plano que utiliza o Foreground Service para permanecer ativo enquanto executa tarefas contínuas.
 */
class BackgroundService : Service() {

    // ID do canal de notificação, usado para identificar o canal no sistema
    private val channelId = "background_service_channel"

    // Variável para controlar o estado do serviço, indicando se ele está em execução
    private var isRunning = false

    /**
     * Método chamado ao criar o serviço. Usado para inicializar recursos necessários.
     */
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel() // Cria um canal de notificação para sistemas Android 8+.
        isRunning = true // Marca o serviço como ativo.
        // Mostra um feedback ao usuário sobre a criação do serviço
        Toast.makeText(this, "Serviço Criado", Toast.LENGTH_SHORT).show()
    }

    /**
     * Método chamado sempre que o serviço é iniciado.
     * @param intent Intent que pode conter dados enviados ao iniciar o serviço.
     * @param flags Configurações adicionais sobre como o serviço deve ser iniciado.
     * @param startId Identificador único para a solicitação de início.
     * @return Tipo de comportamento do serviço em caso de interrupção.
     */
    @SuppressLint("ForegroundServiceType")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Configuração da notificação que mantém o serviço em execução em primeiro plano.
        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Serviço em Execução") // Título exibido na notificação
            .setContentText("O serviço está sendo executado em segundo plano.") // Texto descritivo
            .setSmallIcon(android.R.drawable.ic_menu_info_details) // Ícone da notificação
            .setOngoing(true) // Impede que a notificação seja descartada pelo usuário
            .setPriority(NotificationCompat.PRIORITY_HIGH) // Define a prioridade como alta
            .build()

        // Inicializa o serviço como Foreground Service com a notificação criada
        startForeground(1, notification)
        Toast.makeText(this, "Serviço Iniciado", Toast.LENGTH_SHORT).show()

        // Inicia uma thread separada para simular uma tarefa contínua
        Thread {
            while (isRunning) { // Loop de execução enquanto o serviço estiver ativo
                try {
                    // Simula um processo contínuo com pausa de 1 segundo
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    // Caso a thread seja interrompida, o erro é registrado
                    e.printStackTrace()
                }
            }
            // Para o serviço automaticamente ao sair do loop
            stopSelf()
        }.start()

        // Retorna START_STICKY para que o serviço seja reiniciado pelo sistema após ser encerrado.
        return START_STICKY
    }

    /**
     * Método chamado ao destruir o serviço.
     * Usado para liberar recursos e finalizar tarefas em andamento.
     */
    override fun onDestroy() {
        super.onDestroy()
        isRunning = false // Marca o serviço como não ativo
        // Notifica o usuário que o serviço foi encerrado
        Toast.makeText(this, "Serviço Parado", Toast.LENGTH_SHORT).show()
    }

    /**
     * Método obrigatório para serviços. Como este serviço não é "bindable", retorna null.
     */
    override fun onBind(intent: Intent?): IBinder? = null

    /**
     * Método para criar um canal de notificação. Necessário para dispositivos Android 8.0 (Oreo) e superiores.
     * O canal organiza as notificações associadas a este serviço.
     */
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Configura o canal de notificação com ID, nome e nível de importância
            val channel = NotificationChannel(
                channelId, // Identificador único do canal
                "Serviço em Background", // Nome visível do canal
                NotificationManager.IMPORTANCE_LOW // Prioridade baixa para minimizar interrupções
            )
            // Obtém o gerenciador de notificações do sistema
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel) // Cria o canal de notificação
        }
    }
}

//package com.example.serviceapp.service
//
//import android.annotation.SuppressLint
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.app.Service
//import android.content.Intent
//import android.os.Build
//import android.os.IBinder
//import android.widget.Toast
//import androidx.core.app.NotificationCompat
//
//class BackgroundService : Service() {
//
//    private val channelId = "background_service_channel"
//    private var isRunning = false // Variável para controlar o estado do serviço
//
//    override fun onCreate() {
//        super.onCreate()
//        createNotificationChannel()
//        isRunning = true // Inicia o estado do serviço como ativo
//        Toast.makeText(this, "Serviço Criado", Toast.LENGTH_SHORT).show()
//    }
//
//    @SuppressLint("ForegroundServiceType")
//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        val notification = NotificationCompat.Builder(this, channelId)
//            .setContentTitle("Serviço em Execução")
//            .setContentText("O serviço está sendo executado em segundo plano.")
//            .setSmallIcon(android.R.drawable.ic_menu_info_details)
//            .setOngoing(true) // Torna a notificação fixa
//            .setPriority(NotificationCompat.PRIORITY_HIGH) // Define prioridade alta
//            .build()
//
//        startForeground(1, notification) // Inicia o serviço em primeiro plano
//        Toast.makeText(this, "Serviço Iniciado", Toast.LENGTH_SHORT).show()
//
//        // Simulação de uma tarefa contínua em uma thread separada
//        Thread {
//            while (isRunning) {
//                try {
//                    Thread.sleep(1000) // Simula processamento contínuo
//                } catch (e: InterruptedException) {
//                    e.printStackTrace()
//                }
//            }
//            stopSelf() // Para o serviço quando isRunning for falso
//        }.start()
//
//        return START_STICKY
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        isRunning = false // Define o estado como parado
//        Toast.makeText(this, "Serviço Parado", Toast.LENGTH_SHORT).show()
//    }
//
//    override fun onBind(intent: Intent?): IBinder? = null
//
//    private fun createNotificationChannel() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(
//                channelId,
//                "Serviço em Background",
//                NotificationManager.IMPORTANCE_LOW // Notificação com baixa interferência
//            )
//            val manager = getSystemService(NotificationManager::class.java)
//            manager?.createNotificationChannel(channel)
//        }
//    }
//}
