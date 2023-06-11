package es.sergiop.pepilandaSpringJPA.dto

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