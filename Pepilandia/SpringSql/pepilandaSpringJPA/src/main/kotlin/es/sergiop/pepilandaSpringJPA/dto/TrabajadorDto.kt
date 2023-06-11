package es.sergiop.pepilandaSpringJPA.dto

data class TrabajadorDto(
    val id: Long?,
    val nombre: String,
    val telefono: String,
    val email: String,
    val nombreUsuario: String,
    val password: String,
    val especialidad: String,
    val isResponsable: Boolean,
    var salario: String,
    val fechaContratacion: String
)

data class TrabajadorCreateDto(
    val nombre: String,
    val telefono: String,
    val email: String,
    val nombreUsuario: String,
    val password: String,
    val especialidad: String,
    val isResponsable: Boolean,
    var salario: String,
    val fechaContratacion: String
)