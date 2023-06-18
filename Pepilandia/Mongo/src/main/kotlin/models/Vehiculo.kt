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
 * Clase Vehiculo
 * @param id: Long? = null
 * @param marca: String
 * @param modelo: String
 * @param matricula: String
 * @param fechaMatriculacion: LocalDate
 * @param fechaUltimaRevision: LocalDate
 * @param dniPropietario: String
 */
data class Vehiculo(
    @BsonId @Contextual
    val id: String = newId<Vehiculo>().toString(),
    val marca: String,
    val modelo: String,
    val matricula: String,
    @Serializable(with = LocalDateSerializer::class)
    val fechaMatriculacion: LocalDate,
    @Serializable(with = LocalDateSerializer::class)
    val fechaUltimaRevision: LocalDate,
    val dniPropietario: String
)