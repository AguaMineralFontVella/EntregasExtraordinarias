package es.sergiop.pepilandaSpringJPA.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Clase Cita
 * @param id: Long? = null
 * @param idTrabajador: Int
 * @param idVehiculo: Int
 * @param fecha: LocalDate
 * @param hora: String
 */
@Table(name = "citas")
data class Cita(
    @Id
    val id: Long? = null,
    val idTrabajador: Int,
    val idVehiculo: Int,
    val fecha: LocalDate,
    val hora: String
)