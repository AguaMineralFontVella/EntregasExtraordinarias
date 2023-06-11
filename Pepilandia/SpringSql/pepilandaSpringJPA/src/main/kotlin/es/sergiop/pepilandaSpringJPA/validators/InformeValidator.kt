package es.sergiop.pepilandaSpringJPA.validators

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import es.sergiop.pepilandaSpringJPA.exceptions.InformeException
import es.sergiop.pepilandaSpringJPA.models.Informe
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

fun Informe.validar(): Result<Informe, InformeException> {
    logger.debug { "validar: $this" }
    return when {
        frenado < 1 -> {
            val exception = InformeException.NoValido("frenado: $frenado")
            logger.error { exception.message }
            Err(exception)
        }
        frenado > 10 -> {
            val exception = InformeException.NoValido("frenado: $frenado")
            logger.error { exception.message }
            Err(exception)
        }
        frenado.toString().length > 5 -> {
            val exception = InformeException.NoValido("frenado: $frenado")
            logger.error { exception.message }
            Err(exception)
        }
        contaminacion < 20 -> {
            val exception = InformeException.NoValido("contaminacion: $contaminacion")
            logger.error { exception.message }
            Err(exception)
        }
        contaminacion > 50 -> {
            val exception = InformeException.NoValido("contaminacion: $contaminacion")
            logger.error { exception.message }
            Err(exception)
        }
        contaminacion.toString().length > 5 -> {
            val exception = InformeException.NoValido("contaminacion: $contaminacion")
            logger.error { exception.message }
            Err(exception)
        }
        idCita < 0 -> {
            val exception = InformeException.NoValido("idCita: $idCita")
            logger.error { exception.message }
            Err(exception)
        }
        idTrabajador < 0 -> {
            val exception = InformeException.NoValido("idTrabajador: $idTrabajador")
            logger.error { exception.message }
            Err(exception)
        }
        idVehiculo < 0 -> {
            val exception = InformeException.NoValido("idVehiculo: $idVehiculo")
            logger.error { exception.message }
            Err(exception)
        }
        dniPropietario.isEmpty() -> {
            val exception = InformeException.NoValido("dniPropietario: $dniPropietario")
            logger.error { exception.message }
            Err(exception)
        }
        dniPropietario.length != 9 -> {
            val exception = InformeException.NoValido("dniPropietario: $dniPropietario")
            logger.error { exception.message }
            Err(exception)
        }
        isFavorable < 0 -> {
            val exception = InformeException.NoValido("isFavorable: $isFavorable")
            logger.error { exception.message }
            Err(exception)
        }
        isFavorable >= 2 -> {
            val exception = InformeException.NoValido("isFavorable: $isFavorable")
            logger.error { exception.message }
            Err(exception)
        }
        else -> Ok(this)
    }
}