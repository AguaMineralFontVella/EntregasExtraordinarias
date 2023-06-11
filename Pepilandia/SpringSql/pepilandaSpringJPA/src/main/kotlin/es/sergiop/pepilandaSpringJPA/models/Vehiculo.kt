package es.sergiop.pepilandaSpringJPA.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table(name = "vehiculos")
data class Vehiculo(
    @Id
    val id: Long? = null,
    val marca: String,
    val modelo: String,
    val matricula: String,
    val fechaMatriculacion: LocalDate,
    val fechaUltimaRevision: LocalDate,
    val dniPropietario: String
)