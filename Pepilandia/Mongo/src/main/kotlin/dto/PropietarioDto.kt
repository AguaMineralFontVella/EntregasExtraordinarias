package dto

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Data Transfer Object de los propietarios
 */
data class PropietarioCreateDto (
    val dni: String,
    val nombre: String,
    val apellidos: String,
    val telefono: String
)