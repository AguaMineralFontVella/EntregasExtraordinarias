package controllers

import cache.CitaCacheManager
import com.github.michaelbull.result.*
import exceptions.CitaException
import kotlinx.coroutines.flow.*
import models.Cita
import mu.KotlinLogging
import parser.toCitaDTO
import repository.citaRepository.CitaRepository
import validators.validar

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

private val logger = KotlinLogging.logger {}

/**
 * Clase que gestiona las citas
 * @property citaRepository CitaRepository
 * @property _citasFlow MutableStateFlow<List<Cita>>
 * @property citasFlow StateFlow<List<Cita>>
 */
class CitaController( private val citaRepository: CitaRepository ) {

    private val _citasFlow: MutableStateFlow<List<Cita>> = MutableStateFlow(emptyList())
    val citasFlow: StateFlow<List<Cita>> get() = _citasFlow

    /**
     * Función que encuentra todas las citas
     * @return Result<List<Cita>, CitaException>
     */
    fun findAll(): Result<List<Cita>, CitaException> {
        logger.info { "Encontrando todas las citas" }
        val citas = citaRepository.findAll()
        return if (citas != null) {
            logger.info { "Citas encontradas: $citas" }
            citas.forEach {
                CitaCacheManager.cache.put(it.id.toLong(), it.toCitaDTO())
            }
            Ok(citas)
        } else {
            Err(CitaException.NoEncontrado("Citas no encontradas"))
        }
    }

    /**
     * Función que encuentra una cita por id
     * @param id Long
     * @return Result<Cita, CitaException>
     */
    fun findById(id: Long): Result<Cita, CitaException> {
        logger.info { "Encontrando cita por id: $id" }
        val cita = citaRepository.findById(id)
        return if (cita != null) {
            logger.info { "Cita encontrada: $cita" }
            CitaCacheManager.cache.put(cita.id.toLong(), cita.toCitaDTO())
            Ok(cita)
        } else {
            Err(CitaException.NoEncontrado("Cita no encontrada"))
        }
    }

    /**
     * Función que guarda una cita
     * @param cita Cita
     * @return Result<List<Cita>, CitaException>
     */
    fun save(cita: Cita): Result<Cita, CitaException> {
        cita.validar().onFailure { return Err(CitaException.NoValido(it.message)) }
            .onSuccess { logger.info { " ✅ La cita es válida, guardando cita: $cita" }}
        val citaGuardada = citaRepository.save(cita)
        return if (citaGuardada != null) {
            logger.info { "Cita guardada: $citaGuardada" }
            CitaCacheManager.cache.put(citaGuardada.id.toLong(), citaGuardada.toCitaDTO())
            _citasFlow.value = listOf(citaGuardada)
            Ok(citaGuardada)
        } else {
            Err(CitaException.NoGuardado("Cita no guardada"))
        }
    }

    /**
     * Función que actualiza una cita
     * @param cita Cita
     * @return Result<List<Cita>, CitaException>
     */
    fun update(cita: Cita): Result<Cita, CitaException> {
        cita.validar().onFailure { return Err(CitaException.NoValido(it.message)) }
            .onSuccess { logger.info { " ✅ La cita es válida, actualizando cita: $cita" }}
        val citaActualizada = citaRepository.update(cita)
        return if (citaActualizada != null) {
            logger.info { "Cita actualizada: $citaActualizada" }
            CitaCacheManager.cache.invalidate(cita.id.toLong())
            CitaCacheManager.cache.put(citaActualizada.id.toLong(), citaActualizada.toCitaDTO())
            _citasFlow.value = listOf(citaActualizada)
            Ok(citaActualizada)
        } else {
            Err(CitaException.NoGuardado("Cita no actualizada"))
        }
    }

    /**
     * Función que borra una cita
     * @param cita Cita
     * @return Result<List<Cita>, CitaException>
     */
    fun delete(cita: Cita): Result<Boolean, CitaException> {
        logger.info { "Borrando cita: $cita" }
        val citaBorrada = citaRepository.delete(cita)
        return if (citaBorrada) {
            logger.info { "Cita borrada: $citaBorrada" }
            CitaCacheManager.cache.invalidate(cita.id.toLong())
            _citasFlow.value = listOf(cita)
            Ok(citaBorrada)
        } else {
            Err(CitaException.NoGuardado("Cita no borrada"))
        }
    }
}