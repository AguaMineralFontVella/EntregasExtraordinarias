package es.sergiop.pepilandaSpringJPA.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Clase Propietario
 * @param id: Long? = null
 * @param dni: String
 * @param nombre: String
 * @param apellidos: String
 * @param telefono: String
 */
@Table("propietarios")
data class Propietario(
    @Id
    val id: Long? = null,
    val dni: String,
    val nombre: String,
    val apellidos: String,
    val telefono: String
)