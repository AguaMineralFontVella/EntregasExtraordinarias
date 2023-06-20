package es.sergiop.reintentoextra.models

import java.time.LocalDate

data class Planeta(
    val id: Int,
    val nombre: String,
    val distanciaTierra: Int,
    val fechaViaje: LocalDate,
    val personas: String?
)