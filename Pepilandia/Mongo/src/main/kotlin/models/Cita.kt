package models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import locale.LocalDateSerializer
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.newId
import java.time.LocalDate

/**
 * Clase Cita
 * @param id: Long? = null
 * @param idTrabajador: Int
 * @param idVehiculo: Int
 * @param fecha: LocalDate
 * @param hora: String
 */
data class Cita(
    @BsonId @Contextual
    val id: String = newId<Cita>().toString(),
    val idTrabajador: String,
    val idVehiculo: String,
    @Serializable(with = LocalDateSerializer::class)
    val fecha: LocalDate,
    val hora : String
)