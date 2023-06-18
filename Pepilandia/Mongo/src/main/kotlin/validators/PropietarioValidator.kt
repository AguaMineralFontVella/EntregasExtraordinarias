package validators

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import exceptions.PropietarioException
import models.Propietario
import mu.KotlinLogging

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

private val logger = KotlinLogging.logger {}

/**
 * Función que valida un propietario
 * @return Resultado de la validación
 */
fun Propietario.validar(): Result<Propietario, PropietarioException.NoValido> {
    logger.debug { "validar: $this" }
    return when {
        dni.isEmpty() -> {
            val exception = PropietarioException.NoValido("dni: $dni")
            logger.error { exception.message }
            Err(exception)
        }
        dni.length != 9 -> {
            val exception = PropietarioException.NoValido("dni: $dni")
            logger.error { exception.message }
            Err(exception)
        }
        nombre.isEmpty() -> {
            val exception = PropietarioException.NoValido("nombre: $nombre")
            logger.error { exception.message }
            Err(exception)
        }
        nombre.length > 30 -> {
            val exception = PropietarioException.NoValido("nombre: $nombre")
            logger.error { exception.message }
            Err(exception)
        }
        apellidos.isEmpty() -> {
            val exception = PropietarioException.NoValido("apellidos: $apellidos")
            logger.error { exception.message }
            Err(exception)
        }
        apellidos.length > 100 -> {
            val exception = PropietarioException.NoValido("apellidos: $apellidos")
            logger.error { exception.message }
            Err(exception)
        }
        telefono.isEmpty() -> {
            val exception = PropietarioException.NoValido("telefono: $telefono")
            logger.error { exception.message }
            Err(exception)
        }
        telefono.length > 20 -> {
            val exception = PropietarioException.NoValido("telefono: $telefono")
            logger.error { exception.message }
            Err(exception)
        }
        else -> Ok(this)
    }
}