package com.example.nighteventsapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.foundation.clickable

@Composable
fun FavoritesScreen(navController: NavHostController) {
    // Observa as mudanças no estado de favoritos dinamicamente
    val favoriteEvents = eventList.filter { it.isFavorite.value }

    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        if (favoriteEvents.isEmpty()) {
            item {
                Text(
                    text = "Você ainda não tem eventos favoritos.",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }
        } else {
            items(favoriteEvents) { event ->
                Card(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .clickable {
                            navController.navigate("eventDetails/${event.id}")
                        },
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        // Exibe a imagem do evento
                        Image(
                            painter = painterResource(id = event.imageRes),
                            contentDescription = "Imagem do evento",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(80.dp)
                                .padding(end = 16.dp)
                        )

                        // Exibe os detalhes do evento
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = event.title,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = event.date,
                                style = MaterialTheme.typography.bodySmall
                            )
                            Text(
                                text = event.location,
                                style = MaterialTheme.typography.bodySmall
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = event.description,
                                style = MaterialTheme.typography.bodySmall,
                                maxLines = 2 // Limita o texto a 2 linhas
                            )
                        }

                        // Ícone de favorito
                        Icon(
                            imageVector = if (event.isFavorite.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    // Alterna o estado de favorito
                                    event.isFavorite.value = !event.isFavorite.value
                                }
                        )
                    }
                }
            }
        }
    }
}
