package dto

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Data Transfer Object de las citas
 * @param id Id de la cita
 * @param idTrabajador Id del trabajador
 * @param idVehiculo Id del vehículo
 * @param fecha Fecha de la cita
 * @param hora Hora de la cita
 */
data class CitaDto (
    val id : String,
    val idTrabajador : String,
    val idVehiculo : String,
    val fecha : String,
    val hora : String
)

/**
 * Data Transfer Object de las citas para la creación
 * @param idTrabajador Id del trabajador
 * @param idVehiculo Id del vehículo
 * @param fecha Fecha de la cita
 * @param hora Hora de la cita
 */
data class CitaCreateDto (
    val idTrabajador : String,
    val idVehiculo : String,
    val fecha : String,
    val hora : String
)