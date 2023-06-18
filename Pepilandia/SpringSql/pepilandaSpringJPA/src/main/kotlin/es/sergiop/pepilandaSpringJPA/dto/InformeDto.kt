package es.sergiop.pepilandaSpringJPA.dto

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Objeto InformeDTO para la transmisión de datos
 */
data class InformeDto(
    val id : Long?,
    val frenado: String,
    val frenadoIsApto: String,
    val contaminacion: String,
    val lucesIsApto: String,
    val idCita: String,
    val idTrabajador: String,
    val idVehiculo: String,
    val dniPropietario: String,
    val isFavorable: String
)

/**
 * Objeto InformeCreateDTO para la creación de informes desde la API
 */
data class InformeCreateDto(
    val frenado: String,
    val frenadoIsApto: String,
    val contaminacion: String,
    val lucesIsApto: String,
    val idCita: String,
    val idTrabajador: String,
    val idVehiculo: String,
    val dniPropietario: String,
    val isFavorable: String
)