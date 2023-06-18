package dto

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Data Transfer Object de los informes
 * @param frenado Frenado del vehículo
 * @param frenadoIsApto Si el frenado es apto
 * @param contaminacion Contaminación del vehículo
 * @param lucesIsApto Si las luces son aptas
 * @param idCita Id de la cita
 * @param idTrabajador Id del trabajador
 * @param idVehiculo Id del vehículo
 * @param dniPropietario DNI del propietario
 * @param isFavorable Si el informe es favorable
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