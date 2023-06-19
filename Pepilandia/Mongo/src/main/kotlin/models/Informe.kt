package models

import kotlinx.serialization.Contextual
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.newId

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
data class Informe(
    @BsonId @Contextual
    val id: String = newId<Informe>().toString(),
    val frenado: Double,
    val frenadoIsApto: Boolean,
    val contaminacion: Double,
    val lucesIsApto: Boolean,
    val idCita: String,
    val idTrabajador: String,
    val idVehiculo: String,
    val dniPropietario: String,
    val isFavorable: Int // 0: No, 1: Si
)