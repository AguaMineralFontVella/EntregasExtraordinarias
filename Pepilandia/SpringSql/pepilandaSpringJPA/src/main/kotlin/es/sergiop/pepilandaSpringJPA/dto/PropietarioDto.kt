package es.sergiop.pepilandaSpringJPA.dto

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Objeto PropietarioDTO para la transmisión de datos
 */
data class PropietarioDto (
    val id: Long?,
    val dni: String,
    val nombre: String,
    val apellidos: String,
    val telefono: String
)

/**
 * Objeto PropietarioCreateDTO para la creación de propietarios desde la API
 */
data class PropietarioCreateDto (
    val dni: String,
    val nombre: String,
    val apellidos: String,
    val telefono: String
)