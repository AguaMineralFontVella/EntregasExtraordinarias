package es.sergiop.pepilandaSpringJPA.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Clase Trabajador
 * @param id: Long? = null
 * @param nombre: String
 * @param telefono: String
 * @param email: String
 * @param nombreUsuario: String
 * @param password: String
 * @param especialidad: String
 * @param isResponsable: Boolean
 * @param salario: Double
 * @param fechaContratacion: LocalDate
 */
@Table("trabajadores")
data class Trabajador(
    @Id
    val id: Long? = null,
    val nombre: String,
    val telefono: String,
    val email: String,
    val nombreUsuario: String,
    val password: String,
    val especialidad: String,
    val isResponsable: Boolean,
    var salario: Double = 0.00,
    val fechaContratacion: LocalDate
)