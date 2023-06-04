package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Objeto Propietario serializable para la creación del backup
 * @param id Id del propietario
 * @param dni DNI del propietario
 * @param nombre Nombre del propietario
 * @param apellidos Apellidos del propietario
 * @param telefono Teléfono del propietario
 */
@Serializable
@SerialName("Propietario")
data class Propietario(
    @SerialName("Id") val id: Int,
    @SerialName("DNI") val dni: String,
    @SerialName("Nombre") val nombre: String,
    @SerialName("Apellidos") val apellidos: String,
    @SerialName("Teléfono") val telefono: String
)