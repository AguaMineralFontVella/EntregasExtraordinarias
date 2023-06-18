package es.sergiop.pepilandaSpringJPA.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Clase Informe
 * @param id: Long? = null
 * @param frenado: Double
 * @param frenadoIsApto: Boolean
 * @param contaminacion: Double
 * @param lucesIsApto: Boolean
 * @param idCita: Int
 * @param idTrabajador: Int
 * @param idVehiculo: Int
 * @param dniPropietario: String
 * @param isFavorable: Int // 0: No, 1: Si
 */
@Table("informes")
data class Informe(
    @Id
    val id: Long? = null,
    val frenado: Double,
    val frenadoIsApto: Boolean,
    val contaminacion: Double,
    val lucesIsApto: Boolean,
    val idCita: Int,
    val idTrabajador: Int,
    val idVehiculo: Int,
    val dniPropietario: String,
    val isFavorable: Int // 0: No, 1: Si
)