package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Objeto Cita
 * @param id Id de la cita
 * @param idTrabajador Id del trabajador
 * @param idVehiculo Id del vehículo
 * @param fecha Fecha de la cita
 * @param hora Hora de la cita
 */
data class Cita(
    val id: Int,
    val idTrabajador: Int,
    val idVehiculo: Int,
    val fecha: LocalDate,
    val hora : String
)

/**
 * Objeto CitaDTO para la serialización y creación del backup
 * @param id Id de la cita
 * @param idTrabajador Id del trabajador
 * @param idVehiculo Id del vehículo
 * @param fecha Fecha de la cita
 * @param hora Hora de la cita
 */
@Serializable
@SerialName("Cita")
data class CitaDTO(
    @SerialName("Id_Cita") val id: Int,
    @SerialName("Id_Trabajador") val idTrabajador: String,
    @SerialName("Id_Vehículo") val idVehiculo: String,
    @SerialName("Fecha") val fecha: String,
    @SerialName("Hora") val hora: String,
)