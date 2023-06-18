package validators

import mu.KotlinLogging
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import exceptions.CitaException
import models.Cita

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

private val logger = KotlinLogging.logger {}

/**
 * Función que valida una cita
 * @return Resultado de la validación
 */
fun Cita.validar(): Result<Cita, CitaException> {
    logger.debug { "validar: $this" }
    //No hay mucho que validar si cada id se autogenera...
    return Ok(this)
}