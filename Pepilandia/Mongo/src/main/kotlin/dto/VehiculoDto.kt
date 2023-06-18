package dto

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Data Transfer Object de los vehículos
 */
data class VehiculoCreateDto(
    val marca: String,
    val modelo: String,
    val matricula: String,
    val fechaMatriculacion: String,
    val fechaUltimaRevision: String,
    val dniPropietario: String
)