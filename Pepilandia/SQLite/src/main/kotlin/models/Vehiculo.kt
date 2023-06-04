package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Objeto Vehiculo
 * @param id Id del vehículo
 * @param marca Marca del vehículo
 * @param modelo Modelo del vehículo
 * @param matricula Matrícula del vehículo
 * @param fechaMatriculacion Fecha de matriculación del vehículo
 * @param fechaUltimaRevision Fecha de la última revisión del vehículo
 * @param dniPropietario DNI del propietario del vehículo
 */
data class Vehiculo(
    val id: Int,
    val marca: String,
    val modelo: String,
    val matricula: String,
    val fechaMatriculacion: LocalDate,
    val fechaUltimaRevision: LocalDate,
    val dniPropietario: String
)

/**
 * Objeto VehiculoDTO para la serialización y creación del backup
 * @param id Id del vehículo
 * @param marca Marca del vehículo
 * @param modelo Modelo del vehículo
 * @param matricula Matrícula del vehículo
 * @param fechaMatriculacion Fecha de matriculación del vehículo
 * @param fechaUltimaRevision Fecha de la última revisión del vehículo
 * @param dniPropietario DNI del propietario del vehículo
 */
@Serializable
@SerialName("Vehiculo")
data class VehiculoDTO(
    @SerialName("Id") val id: Int,
    @SerialName("Marca") val marca: String,
    @SerialName("Modelo") val modelo: String,
    @SerialName("Matrícula") val matricula: String,
    @SerialName("Fecha_matrícula") val fechaMatriculacion: String,
    @SerialName("Fecha_última_revisión") val fechaUltimaRevision: String,
    @SerialName("DNI_Propietario") val dniPropietario: String
)