package com.example.planetapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.planetapp.R

@ExperimentalMaterial3Api
@Composable
fun TopAppBarWithMenu(
    onSettingsClick: () -> Unit,
    onHelpClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.Top) {
                Image(
                    painter = painterResource(id = R.drawable.icon),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(30.dp)
                        .padding(end = 8.dp)
                )
                Text(
                    text = "PlanetApp",
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        actions = {
            IconButton(onClick = { expanded = true }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Menu"
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Configurações") },
                    onClick = {
                        expanded = false
                        onSettingsClick()
                    }
                )
                DropdownMenuItem(
                    text = { Text("Ajuda") },
                    onClick = {
                        expanded = false
                        onHelpClick()
                    }
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}
