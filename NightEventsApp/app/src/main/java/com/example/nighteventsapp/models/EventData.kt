package com.example.nighteventsapp.ui.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.nighteventsapp.R

data class Event(
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val location: String,
    val isFavorite: MutableState<Boolean> = mutableStateOf(false),
    val isSubscribed: MutableState<Boolean> = mutableStateOf(false),
    val imageRes: Int

)

val eventList = listOf(
    Event(
        id = 1,
        title = "Conferência de Tecnologia 2024",
        description = "Tendências em tecnologia.",
        date = "2024-12-15",
        location = "Parque Tecnológico",
        isFavorite = mutableStateOf(false),
        isSubscribed = mutableStateOf(false),
        imageRes = R.drawable.img1
    ),
    Event(
        id = 2,
        title = "Exposição de Arte Moderna",
        description = "Explorando a arte contemporânea.",
        date = "2024-12-20",
        location = "Museu de Arte",
        isFavorite = mutableStateOf(false),
        isSubscribed = mutableStateOf(false),
        imageRes = R.drawable.img2
    ),
    Event(
        id = 3,
        title = "Festival de Música",
        description = "O maior festival do ano.",
        date = "2024-12-25",
        location = "Estádio Aberto",
        isFavorite = mutableStateOf(false),
        isSubscribed = mutableStateOf(false),
        imageRes = R.drawable.img3
    ),
    Event(
        id = 4,
        title = "Encontro de Startups",
        description = "Conecte-se com empreendedores.",
        date = "2024-12-18",
        location = "Centro de Inovação",
        isFavorite = mutableStateOf(false),
        isSubscribed = mutableStateOf(false),
        imageRes = R.drawable.img4
    ),
    Event(
        id = 5,
        title = "Feira de Livros",
        description = "Livros para todos os gostos.",
        date = "2024-12-22",
        location = "Biblioteca Municipal",
        isFavorite = mutableStateOf(false),
        isSubscribed = mutableStateOf(false),
        imageRes = R.drawable.img5
    ),
    Event(
        id = 6,
        title = "Congresso de Ciências",
        description = "Avanços e descobertas científicas.",
        date = "2024-12-10",
        location = "Universidade Federal",
        isFavorite = mutableStateOf(false),
        isSubscribed = mutableStateOf(false),
        imageRes = R.drawable.img6
    ),
    Event(
        id = 7,
        title = "Festival Gastronômico",
        description = "Sabores e aromas únicos.",
        date = "2024-12-17",
        location = "Praça Central",
        isFavorite = mutableStateOf(false),
        isSubscribed = mutableStateOf(false),
        imageRes = R.drawable.img7
    ),
    Event(
        id = 8,
        title = "Maratona de Programação",
        description = "Desafios de código.",
        date = "2024-12-19",
        location = "Auditório TechLab",
        isFavorite = mutableStateOf(false),
        isSubscribed = mutableStateOf(false),
        imageRes = R.drawable.img8
    ),
    Event(
        id = 9,
        title = "Workshop de Fotografia",
        description = "Aprenda técnicas profissionais.",
        date = "2024-12-08",
        location = "Estúdio Visual",
        isFavorite = mutableStateOf(false),
        isSubscribed = mutableStateOf(false),
        imageRes = R.drawable.img9
    ),
    Event(
        id = 10,
        title = "Palestra de Empreendedorismo",
        description = "Inspire-se com histórias de sucesso.",
        date = "2024-12-14",
        location = "Centro de Convenções",
        isFavorite = mutableStateOf(false),
        isSubscribed = mutableStateOf(false),
        imageRes = R.drawable.img10
    )
)
