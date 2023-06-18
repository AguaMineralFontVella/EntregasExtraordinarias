package models

import kotlinx.serialization.Contextual
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.newId

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
data class Propietario(
    @BsonId @Contextual
    val id: String = newId<Propietario>().toString(),
    val dni: String,
    val nombre: String,
    val apellidos: String,
    val telefono: String
)