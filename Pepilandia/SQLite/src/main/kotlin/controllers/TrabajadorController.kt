package controllers

import com.github.michaelbull.result.*
import exceptions.TrabajadorException
import models.Trabajador
import mu.KotlinLogging
import repository.trabajadorRepository.TrabajadorRepository
import validators.validar

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

private val logger = KotlinLogging.logger {}

/**
 * Clase que gestiona los trabajadores
 * @property trabajadorRepository TrabajadorRepository
 */
class TrabajadorController ( private val trabajadorRepository: TrabajadorRepository){

    /**
     * Función que encuentra todos los trabajadores por su teléfono
     * @param telefono String
     * @return Result<List<Trabajador>, TrabajadorException>
     */
    fun findByTelefono(telefono: String): Result<Trabajador, TrabajadorException>{
        logger.info { "Encontrando trabajador por telefono: $telefono" }
        val trabajador = trabajadorRepository.findByTelefono(telefono)
        return if (trabajador != null) {
            logger.info { "Trabajador encontrado: $trabajador" }
            Ok(trabajador)
        } else {
            Err(TrabajadorException.NoEncontrado("Trabajador no encontrado"))
        }
    }

    /**
     * Función que encuentra todos los trabajadores por su email
     * @param email String
     * @return Result<List<Trabajador>, TrabajadorException>
     */
    fun findByEmail(email: String): Result<Trabajador, TrabajadorException>{
        logger.info { "Encontrando trabajador por email: $email" }
        val trabajador = trabajadorRepository.findByEmail(email)
        return if (trabajador != null) {
            logger.info { "Trabajador encontrado: $trabajador" }
            Ok(trabajador)
        } else {
            Err(TrabajadorException.NoEncontrado("Trabajador no encontrado"))
        }
    }

    /**
     * Función que encuentra todos los trabajadores por su nombre de usuario
     * @param nombreUsuario String
     * @return Result<List<Trabajador>, TrabajadorException>
     */
    fun findByNombreUsuario(nombreUsuario: String): Result<Trabajador, TrabajadorException>{
        logger.info { "Encontrando trabajador por nombre de usuario: $nombreUsuario" }
        val trabajador = trabajadorRepository.findByNombreUsuario(nombreUsuario)
        return if (trabajador != null) {
            logger.info { "Trabajador encontrado: $trabajador" }
            Ok(trabajador)
        } else {
            Err(TrabajadorException.NoEncontrado("Trabajador no encontrado"))
        }
    }

    /**
     * Función que encuentra todos los trabajadores por su especialidad
     * @param especialidad String
     * @return Result<List<Trabajador>, TrabajadorException>
     */
    fun findByEspecialidad(especialidad: String): Result<List<Trabajador>, TrabajadorException>{
        logger.info { "Encontrando trabajador por especialidad: $especialidad" }
        val trabajadores = trabajadorRepository.findByEspecialidad(especialidad)
        return if (trabajadores.isNotEmpty()) {
            logger.info { "Trabajadores encontrados: $trabajadores" }
            Ok(trabajadores)
        } else {
            Err(TrabajadorException.NoEncontrado("Trabajadores no encontrados"))
        }
    }

    /**
     * Función que encuentra todos los trabajadores en función de si isResponsable es true o false
     * @return Result<List<Trabajador>, TrabajadorException>
     */
    fun findByIsResponsable(): Result<List<Trabajador>, TrabajadorException>{
        logger.info { "Encontrando trabajador/es por isResponsable: true" }
        val trabajadores = trabajadorRepository.findByIsResponsable(true)
        return if (trabajadores.isNotEmpty()) {
            logger.info { "Trabajadores encontrados con isResponsable true: $trabajadores" }
            Ok(trabajadores)
        } else {
            Err(TrabajadorException.NoEncontrado("Trabajadores no encontrados"))
        }
    }

    /**
     * Función que encuentra todos los trabajadores
     * @return Result<List<Trabajador>, TrabajadorException>
     */
    fun findAll(): Result<List<Trabajador>, TrabajadorException> {
        logger.info { "Encontrando todos los trabajadores" }
        val trabajadores = trabajadorRepository.findAll()
        return if (trabajadores.isNotEmpty()) {
            logger.info { "Trabajadores encontrados: $trabajadores" }
            Ok(trabajadores)
        } else {
            Err(TrabajadorException.NoEncontrado("Trabajadores no encontrados"))
        }
    }

    /**
     * Función que encuentra un trabajador por su id
     * @param id Long
     * @return Result<Trabajador, TrabajadorException>
     */
    fun findById(id: Long): Result<Trabajador, TrabajadorException> {
        logger.info { "Encontrando trabajador por id: $id" }
        val trabajador = trabajadorRepository.findById(id)
        return if (trabajador != null) {
            logger.info { "Trabajador encontrado: $trabajador" }
            Ok(trabajador)
        } else {
            Err(TrabajadorException.NoEncontrado("Trabajador no encontrado"))
        }
    }

    /**
     * Función que guarda un trabajador
     * @param trabajador Trabajador
     * @return Result<Trabajador, TrabajadorException>
     */
    fun save(trabajador: Trabajador): Result<Trabajador, TrabajadorException> {
        trabajador.validar().onFailure { return Err(TrabajadorException.NoValido(it.message)) }
            .onSuccess { logger.info { " ✅ El trabajador es válido, guardando trabajador: $trabajador" } }
        val trabajadorGuardado = trabajadorRepository.save(trabajador)
        return if (trabajadorGuardado != null) {
            logger.info { "Trabajador guardado: $trabajadorGuardado" }
            Ok(trabajadorGuardado)
        } else {
            Err(TrabajadorException.NoGuardado("Error al guardar trabajador"))
        }
    }

    /**
     * Función que actualiza un trabajador
     * @param trabajador Trabajador
     * @return Result<Trabajador, TrabajadorException>
     */
    fun update(trabajador: Trabajador): Result<Trabajador, TrabajadorException> {
        trabajador.validar().onFailure { return Err(TrabajadorException.NoValido(it.message)) }
            .onSuccess { logger.info { " ✅ El trabajador es válido, actualizando trabajador: $trabajador" } }
        val trabajadorActualizado = trabajadorRepository.update(trabajador)
        return if (trabajadorActualizado != null) {
            logger.info { "Trabajador actualizado: $trabajadorActualizado" }
            Ok(trabajadorActualizado)
        } else {
            Err(TrabajadorException.NoGuardado("Error al actualizar trabajador"))
        }
    }

    /**
     * Función que elimina un trabajador
     * @param trabajador Trabajador
     * @return Result<Boolean, TrabajadorException>
     */
    fun delete(trabajador: Trabajador): Result<Boolean, TrabajadorException> {
        logger.info { "Eliminando trabajador: $trabajador" }
        val trabajadorEliminado = trabajadorRepository.delete(trabajador)
        return if (trabajadorEliminado) {
            logger.info { "Trabajador eliminado: $trabajadorEliminado" }
            Ok(trabajadorEliminado)
        } else {
            Err(TrabajadorException.NoGuardado("Error al eliminar trabajador"))
        }
    }

}