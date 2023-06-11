package es.sergiop.pepilandaSpringJPA.dto

data class VehiculoDto (
    val id: Long?,
    val marca: String,
    val modelo: String,
    val matricula: String,
    val fechaMatriculacion: String,
    val fechaUltimaRevision: String,
    val dniPropietario: String
)

data class VehiculoCreateDto(
    val marca: String,
    val modelo: String,
    val matricula: String,
    val fechaMatriculacion: String,
    val fechaUltimaRevision: String,
    val dniPropietario: String
)