package es.sergiop.pepilandaSpringJPA.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table(name = "citas")
data class Cita(
    @Id
    val id: Long? = null,
    val idTrabajador: Int,
    val idVehiculo: Int,
    val fecha: LocalDate,
    val hora: String
)