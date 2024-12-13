package com.example.planetapp.models

import com.example.planetapp.R

data class Planet(
    val id: Int,
    val name: String,
    val type: String,
    val galaxy: String,
    val distanceFromSun: String,
    val diameter: String,
    val characteristics: String,
    val imageRes: Int,
    var isFavorite: Boolean = false
)

val planetList = listOf(
    Planet(
        id = 1,
        name = "Terra",
        type = "Terrestre",
        galaxy = "Via Láctea",
        distanceFromSun = "149,6 milhões de km",
        diameter = "12.742 km",
        characteristics = "Suporta vida, possui água e atmosfera.",
        imageRes = R.drawable.terra
    ),
    Planet(
        id = 2,
        name = "Marte",
        type = "Terrestre",
        galaxy = "Via Láctea",
        distanceFromSun = "227,9 milhões de km",
        diameter = "6.779 km",
        characteristics = "Conhecido como o planeta vermelho, possui óxido de ferro na superfície.",
        imageRes = R.drawable.marte
    ),
    Planet(
        id = 3,
        name = "Mercúrio",
        type = "Terrestre",
        galaxy = "Via Láctea",
        distanceFromSun = "57,9 milhões de km",
        diameter = "4.879 km",
        characteristics = "O planeta mais próximo do Sol, sem atmosfera significativa.",
        imageRes = R.drawable.mercurio
    ),
    Planet(
        id = 4,
        name = "Vênus",
        type = "Terrestre",
        galaxy = "Via Láctea",
        distanceFromSun = "108,2 milhões de km",
        diameter = "12.104 km",
        characteristics = "Possui atmosfera densa e quente, conhecida como a 'irmã' da Terra.",
        imageRes = R.drawable.venus
    ),
    Planet(
        id = 5,
        name = "Júpiter",
        type = "Gasoso",
        galaxy = "Via Láctea",
        distanceFromSun = "778,5 milhões de km",
        diameter = "139.820 km",
        characteristics = "Maior planeta do Sistema Solar, famoso por sua Grande Mancha Vermelha.",
        imageRes = R.drawable.jupiter
    ),
    Planet(
        id = 6,
        name = "Saturno",
        type = "Gasoso",
        galaxy = "Via Láctea",
        distanceFromSun = "1,4 bilhões de km",
        diameter = "116.460 km",
        characteristics = "Conhecido por seus impressionantes anéis formados por gelo e rochas.",
        imageRes = R.drawable.saturno
    ),
    Planet(
        id = 7,
        name = "Urano",
        type = "Gasoso",
        galaxy = "Via Láctea",
        distanceFromSun = "2,9 bilhões de km",
        diameter = "50.724 km",
        characteristics = "Planeta inclinado, com eixo quase paralelo ao plano orbital.",
        imageRes = R.drawable.urano
    ),
    Planet(
        id = 8,
        name = "Netuno",
        type = "Gasoso",
        galaxy = "Via Láctea",
        distanceFromSun = "4,5 bilhões de km",
        diameter = "49.244 km",
        characteristics = "Conhecido por seus ventos fortes e cor azul vibrante.",
        imageRes = R.drawable.netuno
    ),
    Planet(
        id = 9,
        name = "Plutão",
        type = "Anão",
        galaxy = "Via Láctea",
        distanceFromSun = "5,9 bilhões de km",
        diameter = "2.377 km",
        characteristics = "Planeta anão no Cinturão de Kuiper, possui uma superfície composta por gelo e metano.",
        imageRes = R.drawable.plutao
    )

)

