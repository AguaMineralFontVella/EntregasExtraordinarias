package models

import kotlinx.serialization.Contextual

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Clase Response
 * @param T
 */
sealed class Response<T>
class ResponseSuccess<T: Any>(val code: Int, val data: T) : Response<T>()
class ResponseError(val code: Int, val message: String?) : Response<@Contextual Nothing>()