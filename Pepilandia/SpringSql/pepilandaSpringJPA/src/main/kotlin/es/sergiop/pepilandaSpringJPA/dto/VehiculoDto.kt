package es.sergiop.pepilandaSpringJPA.dto

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Objeto VehiculoDTO para la transmisión de datos
 */
data class VehiculoDto (
    val id: Long?,
    val marca: String,
    val modelo: String,
    val matricula: String,
    val fechaMatriculacion: String,
    val fechaUltimaRevision: String,
    val dniPropietario: String
)

/**
 * Objeto VehiculoCreateDTO para la creación de vehiculos desde la API
 */
data class VehiculoCreateDto(
    val marca: String,
    val modelo: String,
    val matricula: String,
    val fechaMatriculacion: String,
    val fechaUltimaRevision: String,
    val dniPropietario: String
)