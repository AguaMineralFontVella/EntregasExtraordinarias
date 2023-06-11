package es.sergiop.pepilandaSpringJPA.dto

data class PropietarioDto (
    val id: Long?,
    val dni: String,
    val nombre: String,
    val apellidos: String,
    val telefono: String
)

data class PropietarioCreateDto (
    val dni: String,
    val nombre: String,
    val apellidos: String,
    val telefono: String
)