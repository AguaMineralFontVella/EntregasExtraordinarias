package dto

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Data Transfer Object de los trabajadores
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