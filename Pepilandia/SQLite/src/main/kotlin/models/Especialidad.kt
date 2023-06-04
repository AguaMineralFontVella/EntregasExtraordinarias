package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Objeto Especialidad serializable para la creación del backup
 * @param nombre Nombre de la especialidad
 * @param salarioBase Salario base de la especialidad
 */
@Serializable
data class Especialidad(
    @SerialName("Nombre") val nombre: String,
    @SerialName("Salario_base") val salarioBase: Double
)