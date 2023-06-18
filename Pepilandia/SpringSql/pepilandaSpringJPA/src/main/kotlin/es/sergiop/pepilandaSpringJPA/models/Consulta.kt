package es.sergiop.pepilandaSpringJPA.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Clase que representa una consulta
 * @param trabajadorMasGana: String
 * @param salarioMedio: Double
 * @param salarioMedioEspecialidad: Map<String, Double>
 * @param trabajadorMenosAntiguedad: String
 * @param trabajadoresOrdenados: String
 */
@Serializable
data class Consulta(
    @SerialName("Trabajador que más gana") val trabajadorMasGana: String,
    @SerialName("Salario medio") val salarioMedio: Double,
    @SerialName("Salario medio por especialidad")val salarioMedioEspecialidad: Map<String, Double>,
    @SerialName("Trabajador con menor antiguedad")val trabajadorMenosAntiguedad: String,
    @SerialName("Trabajadores ordenados por especialidad y ordenados")val trabajadoresOrdenados: String
)