package validators

import exceptions.CitaException
import models.Cita
import mu.KotlinLogging
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import java.time.LocalDate

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
    return when {
        id < 0 -> {
            val exception = CitaException.NoValido("id: $id")
            logger.error { exception.message }
            Err(exception)
        }
        idTrabajador < 0 -> {
            val exception = CitaException.NoValido("idTrabajador: $idTrabajador")
            logger.error { exception.message }
            Err(exception)
        }
        idVehiculo < 0 -> {
            val exception = CitaException.NoValido("idVehiculo: $idVehiculo")
            logger.error { exception.message }
            Err(exception)
        }
        fecha.isAfter(LocalDate.now()) -> {
            val exception = CitaException.NoValido("fechaHora: $fecha")
            logger.error { exception.message }
            Err(exception)
        }
        else -> Ok(this)
    }
}