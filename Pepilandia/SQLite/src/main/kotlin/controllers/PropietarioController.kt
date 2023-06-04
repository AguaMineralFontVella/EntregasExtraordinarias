package controllers

import com.github.michaelbull.result.*
import exceptions.PropietarioException
import models.Propietario
import mu.KotlinLogging
import repository.propietarioRepository.PropietarioRepository
import validators.validar

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

private val logger = KotlinLogging.logger {}

/**
 * Clase que gestiona los propietarios
 * @property propietarioRepository PropietarioRepository
 */
class PropietarioController( private val propietarioRepository: PropietarioRepository ){

    /**
     * Función que encuentra todos los propietarios
     * @return Result<List<Propietario>, PropietarioException>
     */
    fun findAll(): Result<List<Propietario>, PropietarioException> {
        logger.info { "Encontrando todos los propietarios" }
        val propietarios = propietarioRepository.findAll()
        return if (propietarios.isNotEmpty()) {
            logger.info { "Propietarios encontrados: $propietarios" }
            Ok(propietarios)
        } else {
            Err(PropietarioException.NoEncontrado("Propietarios no encontrados"))
        }
    }

    /**
     * Función que encuentra un propietario por id
     * @param id Long
     * @return Result<Propietario, PropietarioException>
     */
    fun findById(id: Long): Result<Propietario, PropietarioException> {
        logger.info { "Encontrando propietario por id: $id" }
        val propietario = propietarioRepository.findById(id)
        return if (propietario != null) {
            logger.info { "Propietario encontrado: $propietario" }
            Ok(propietario)
        } else {
            Err(PropietarioException.NoEncontrado("Propietario no encontrado"))
        }
    }

    /**
     * Función que guarda un propietario
     * @param propietario Propietario
     * @return Result<Propietario, PropietarioException>
     */
    fun save(propietario: Propietario): Result<Propietario, PropietarioException> {
        propietario.validar().onFailure { return Err(PropietarioException.NoValido(it.message)) }
            .onSuccess { logger.info { " ✅ El propietario es válido, guardando propietario: $propietario" }}
        val propietarioGuardado = propietarioRepository.save(propietario)
        return if (propietarioGuardado != null) {
            logger.info { "Propietario guardado: $propietarioGuardado" }
            Ok(propietarioGuardado)
        } else {
            Err(PropietarioException.NoGuardado("Propietario no guardado"))
        }
    }

    /**
     * Función que actualiza un propietario
     * @param propietario Propietario
     * @return Result<Propietario, PropietarioException>
     */
    fun update(propietario: Propietario): Result<Propietario, PropietarioException> {
        propietario.validar().onFailure { return Err(PropietarioException.NoValido(it.message)) }
            .onSuccess { logger.info { " ✅ El propietario es válido, actualizando propietario: $propietario" }}
        val propietarioActualizado = propietarioRepository.update(propietario)
        return if (propietarioActualizado != null) {
            logger.info { "Propietario actualizado: $propietarioActualizado" }
            Ok(propietarioActualizado)
        } else {
            Err(PropietarioException.NoGuardado("Propietario no actualizado"))
        }
    }

    /**
     * Función que elimina un propietario
     * @param propietario Propietario
     * @return Result<Boolean, PropietarioException>
     */
    fun delete(propietario: Propietario): Result<Boolean, PropietarioException> {
        logger.info { "Eliminando propietario: $propietario" }
        val propietarioEliminado = propietarioRepository.delete(propietario)
        return if (propietarioEliminado) {
            logger.info { "Propietario eliminado: $propietarioEliminado" }
            Ok(propietarioEliminado)
        } else {
            Err(PropietarioException.NoGuardado("Propietario no eliminado"))
        }
    }
}