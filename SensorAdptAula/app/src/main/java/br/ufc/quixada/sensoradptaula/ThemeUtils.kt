package br.ufc.quixada.sensoradptaula

import java.util.*

fun getCurrentTheme(): Boolean {
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    return currentHour in 18..23 || currentHour in 0..6 // Modo escuro entre 18h e 6h
}