package com.example.resistencias.scream

fun calculadoraResistencias(banda1: Int, banda2: Int,multiplicador: Int): String {
    val resistenciaValue = (banda1 * 10 + banda2) * Math.pow(10.0, multiplicador.toDouble())
    return resistenciaValue.toString()
}



