package validators

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import exceptions.VehiculoException
import models.Vehiculo
import mu.KotlinLogging

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

private val logger = KotlinLogging.logger {}

/**
 * Función que valida un vehiculo
 * @return Resultado de la validación
 */
fun Vehiculo.validar(): Result<Vehiculo, VehiculoException.NoValido> {
    logger.debug { "validar: $this" }
    return when {
        marca.isEmpty() -> {
            val exception = VehiculoException.NoValido("marca: $marca")
            logger.error { exception.message }
            Err(exception)
        }
        marca.length > 50 -> {
            val exception = VehiculoException.NoValido("marca: $marca")
            logger.error { exception.message }
            Err(exception)
        }
        modelo.isEmpty() -> {
            val exception = VehiculoException.NoValido("modelo: $modelo")
            logger.error { exception.message }
            Err(exception)
        }
        modelo.length > 50 -> {
            val exception = VehiculoException.NoValido("modelo: $modelo")
            logger.error { exception.message }
            Err(exception)
        }
        matricula.isEmpty() -> {
            val exception = VehiculoException.NoValido("matricula: $matricula")
            logger.error { exception.message }
            Err(exception)
        }
        matricula.length > 10 -> {
            val exception = VehiculoException.NoValido("matricula: $matricula")
            logger.error { exception.message }
            Err(exception)
        }
        dniPropietario.isEmpty() -> {
            val exception = VehiculoException.NoValido("dniPropietario: $dniPropietario")
            logger.error { exception.message }
            Err(exception)
        }
        dniPropietario.length > 9 -> {
            val exception = VehiculoException.NoValido("dniPropietario: $dniPropietario")
            logger.error { exception.message }
            Err(exception)
        }
        else -> Ok(this)
    }
}