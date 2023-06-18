package validators

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import exceptions.TrabajadorException
import models.Trabajador
import mu.KotlinLogging
import java.time.LocalDate

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

private val logger = KotlinLogging.logger {}

/**
 * Función que valida un trabajador
 * @return Resultado de la validación
 */
fun Trabajador.validar(trabajadores: List<Trabajador>): Result<Trabajador, TrabajadorException.NoValido> {
    logger.debug { "Validando: $this" }
    return when {
        nombre.isEmpty() -> {
            val exception = TrabajadorException.NoValido("nombre: $nombre")
            logger.error { exception.message }
            Err(exception)
        }
        nombre.length > 30 -> {
            val exception = TrabajadorException.NoValido("nombre: $nombre")
            logger.error { exception.message }
            Err(exception)
        }
        telefono.isEmpty() -> {
            val exception = TrabajadorException.NoValido("teléfono: $telefono")
            logger.error { exception.message }
            Err(exception)
        }
        telefono.length > 20 -> {
            val exception = TrabajadorException.NoValido("teléfono: $telefono")
            logger.error { exception.message }
            Err(exception)
        }
        email.isEmpty() -> {
            val exception = TrabajadorException.NoValido("email: $email")
            logger.error { exception.message }
            Err(exception)
        }
        email.length > 100 -> {
            val exception = TrabajadorException.NoValido("email: $email")
            logger.error { exception.message }
            Err(exception)
        }
        trabajadores.any { it.email == email } -> {
            val exception = TrabajadorException.NoValido("email repetido: $email")
            logger.error { exception.message }
            Err(exception)
        }
        nombreUsuario.isEmpty() -> {
            val exception = TrabajadorException.NoValido("nombreUsuario: $nombreUsuario")
            logger.error { exception.message }
            Err(exception)
        }
        nombreUsuario.length > 20 -> {
            val exception = TrabajadorException.NoValido("nombreUsuario: $nombreUsuario")
            logger.error { exception.message }
            Err(exception)
        }
        trabajadores.any { it.nombreUsuario == nombreUsuario } -> {
            val exception = TrabajadorException.NoValido("nombreUsuario repetido: $nombreUsuario")
            logger.error { exception.message }
            Err(exception)
        }
        password.isEmpty() -> {
            val exception = TrabajadorException.NoValido("password: $password")
            logger.error { exception.message }
            Err(exception)
        }
        password.length > 100 -> {
            val exception = TrabajadorException.NoValido("password: $password")
            logger.error { exception.message }
            Err(exception)
        }
        especialidad.isEmpty() -> {
            val exception = TrabajadorException.NoValido("especialidad: $especialidad")
            logger.error { exception.message }
            Err(exception)
        }
        especialidad != "ELECTRICIDAD" && especialidad != "MOTOR" && especialidad != "MECANICA" && especialidad != "INTERIOR" -> {
            val exception = TrabajadorException.NoValido("especialidad: $especialidad")
            logger.error { exception.message }
            Err(exception)
        }
        salario < 0 -> {
            val exception = TrabajadorException.NoValido("salario: $salario")
            logger.error { exception.message }
            Err(exception)
        }
        salario.toString().length > 12 -> {
            val exception = TrabajadorException.NoValido("salario: $salario")
            logger.error { exception.message }
            Err(exception)
        }
        fechaContratacion.isAfter(LocalDate.now()) -> {
            val exception = TrabajadorException.NoValido("fechaContratacion: $fechaContratacion")
            logger.error { exception.message }
            Err(exception)
        }
        else -> Ok(this)
    }



}