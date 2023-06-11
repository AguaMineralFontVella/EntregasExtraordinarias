package es.sergiop.pepilandaSpringJPA.dto

data class CitaDto (
    val id : Long?,
    val idTrabajador : String,
    val idVehiculo : String,
    val fecha : String,
    val hora : String
)

data class CitaCreateDto (
    val idTrabajador : String,
    val idVehiculo : String,
    val fecha : String,
    val hora : String
)