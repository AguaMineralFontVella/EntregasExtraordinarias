package es.sergiop.pepilandaSpringJPA.dto

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Objeto VehiculoDTO para la transmisión de datos
 */
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

/**
 * Objeto VehiculoCreateDTO para la creación de vehiculos desde la API
 */
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