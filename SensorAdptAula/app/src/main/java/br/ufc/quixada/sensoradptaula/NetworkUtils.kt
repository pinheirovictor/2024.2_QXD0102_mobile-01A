package br.ufc.quixada.sensoradptaula

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast

fun checkNetworkType(context: Context) {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork
    val capabilities = connectivityManager.getNetworkCapabilities(network)

    val networkType = when {
        capabilities == null -> "Sem Conexão"
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> "Conectado via Wi-Fi"
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> "Conectado via Dados Móveis"
        else -> "Tipo de Conexão Desconhecido"
    }

    Toast.makeText(context, networkType, Toast.LENGTH_SHORT).show()
}

// BroadcastReceiver para detectar mudanças de rede automaticamente
class NetworkChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null) {
            checkNetworkType(context)
        }
    }
}
