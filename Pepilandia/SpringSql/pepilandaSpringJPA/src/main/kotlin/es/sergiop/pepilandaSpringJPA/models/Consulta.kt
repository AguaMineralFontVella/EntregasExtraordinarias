package es.sergiop.pepilandaSpringJPA.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Consulta(
    @SerialName("Trabajador que más gana") val trabajadorMasGana: String,
    @SerialName("Salario medio") val salarioMedio: Double,
    @SerialName("Salario medio por especialidad")val salarioMedioEspecialidad: Map<String, Double>,
    @SerialName("Trabajador con menor antiguedad")val trabajadorMenosAntiguedad: String,
    @SerialName("Trabajadores ordenados por especialidad y ordenados")val trabajadoresOrdenados: String
)