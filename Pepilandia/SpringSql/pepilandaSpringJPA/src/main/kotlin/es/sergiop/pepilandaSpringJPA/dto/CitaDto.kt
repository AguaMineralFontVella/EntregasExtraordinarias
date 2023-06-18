package es.sergiop.pepilandaSpringJPA.dto

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Objeto CitaDTO para la transmisión de datos
 */
data class CitaDto (
    val id : Long?,
    val idTrabajador : String,
    val idVehiculo : String,
    val fecha : String,
    val hora : String
)

/**
 * Objeto CitaCreateDTO para la creación de citas desde la API
 */
data class CitaCreateDto (
    val idTrabajador : String,
    val idVehiculo : String,
    val fecha : String,
    val hora : String
)