package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Objeto Trabajador
 * @param id Id del trabajador
 * @param nombre Nombre del trabajador
 * @param telefono Teléfono del trabajador
 * @param email Email del trabajador
 * @param nombreUsuario Nombre de usuario del trabajador
 * @param password Contraseña del trabajador
 * @param especialidad Especialidad del trabajador
 * @param isResponsable Si el trabajador es responsable o no
 * @param salario Salario del trabajador
 * @param fechaContratacion Fecha de contratación del trabajador
 */
data class Trabajador(
    val id: Int,
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

/**
 * Objeto TrabajadorDTO para la serialización y creación del backup
 * @param id Id del trabajador
 * @param nombre Nombre del trabajador
 * @param telefono Teléfono del trabajador
 * @param email Email del trabajador
 * @param nombreUsuario Nombre de usuario del trabajador
 * @param password Contraseña del trabajador
 * @param especialidad Especialidad del trabajador
 * @param isResponsable Si el trabajador es responsable o no
 * @param salario Salario del trabajador
 * @param fechaContratacion Fecha de contratación del trabajador
 */
@Serializable
@SerialName("Trabajador")
data class TrabajadorDTO(
    @SerialName("Id") val id: Int,
    @SerialName("Nombre") val nombre: String,
    @SerialName("Teléfono") val telefono: String,
    @SerialName("Email") val email: String,
    @SerialName("Nombre_Usuario") val nombreUsuario: String,
    @SerialName("Contraseña") val password: String,
    @SerialName("Especialidad") val especialidad: String,
    @SerialName("Is_responsable") val isResponsable: Int, //Ya que en SQLite no hay booleanos
    @SerialName("Salario") val salario: Double = 0.00,
    @SerialName("Fecha_contratación") val fechaContratacion: String
)