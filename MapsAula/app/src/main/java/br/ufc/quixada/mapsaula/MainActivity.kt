package br.ufc.quixada.mapsaula

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.model.*
import com.google.maps.android.compose.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Solicitação de permissão para localização
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permissão concedida
            }
        }

        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            locationPermissionRequest.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }

        setContent {
            MapsScreen()
        }
    }
}

@Composable
fun MapsScreen() {
    val UFC = LatLng(-3.746580, -38.577778)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(UFC, 12f)
    }

    var markerPosition by remember { mutableStateOf<LatLng?>(null) }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(isMyLocationEnabled = true), // Habilita a localização do usuário
        uiSettings = MapUiSettings(myLocationButtonEnabled = true), // Botão de localização
        onMapClick = { latLng ->
            markerPosition = latLng
        }
    ) {
        Marker(
            state = MarkerState(position = UFC),
            title = "UFC",
            snippet = "Campus Quixadá"
        )

        // Adiciona marcador ao clicar no mapa
        markerPosition?.let {
            Marker(
                state = MarkerState(position = it),
                title = "Novo Marcador",
                snippet = "Coordenadas: ${it.latitude}, ${it.longitude}"
            )
        }
    }
}



