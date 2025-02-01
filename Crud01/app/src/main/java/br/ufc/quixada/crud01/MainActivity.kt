package br.ufc.quixada.crud01

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.ufc.quixada.crud01.ui.theme.Crud01Theme
import br.ufc.quixada.crud01.ui.view.ItemScreen


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Crud01Theme {
                Scaffold(
                    modifier = Modifier.fillMaxWidth(),
                    content = { paddingValues ->
                        ItemScreen(
                            modifier = Modifier
                                .padding(paddingValues)
                                .padding(top = 16.dp)
                        )
                    }

                )
            }
        }

    }
}