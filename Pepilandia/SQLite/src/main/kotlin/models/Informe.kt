package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Objeto Informe serializable para la creación del backup
 * @param id Id del informe
 * @param frenado Frenado del vehículo
 * @param frenadoIsApto Si el frenado es apto
 * @param contaminacion Contaminación del vehículo
 * @param lucesIsApto Si las luces son aptas
 * @param idCita Id de la cita
 * @param idTrabajador Id del trabajador
 * @param idVehiculo Id del vehículo
 * @param dniPropietario DNI del propietario
 * @param isFavorable Si el informe es favorable. 0: No, 1: Si
 */
@Serializable
@SerialName("Informe")
data class Informe(
    @SerialName("Id") val id: Int,
    @SerialName("Frenado") val frenado: Double,
    @SerialName("Frenado_is_apto") val frenadoIsApto: Boolean,
    @SerialName("Contaminación") val contaminacion: Double,
    @SerialName("Luces_is_apto") val lucesIsApto: Boolean,
    @SerialName("Id_Cita") val idCita: Int,
    @SerialName("Id_Trabajador") val idTrabajador: Int,
    @SerialName("Id_Vehículo") val idVehiculo: Int,
    @SerialName("DNI_Propietario") val dniPropietario: String,
    @SerialName("Is_favorable") val isFavorable: Int // 0: No, 1: Si
)