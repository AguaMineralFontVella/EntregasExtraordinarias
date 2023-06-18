package models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import locale.LocalDateSerializer
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.newId
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
@Serializable
data class Trabajador(
    @BsonId @Contextual
    val id: String = newId<Trabajador>().toString(),
    val nombre: String,
    val telefono: String,
    val email: String,
    val nombreUsuario: String,
    val password: String,
    val especialidad: String,
    val isResponsable: Boolean,
    var salario: Double,
    @Serializable(with = LocalDateSerializer::class)
    val fechaContratacion: LocalDate
)