package controllers

import cache.InformeCacheManager
import com.github.michaelbull.result.*
import exceptions.InformeException
import models.Informe
import mu.KotlinLogging
import repository.informeRepository.InformeRepository
import validators.validar

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

private val logger = KotlinLogging.logger {}

/**
 * Clase que gestiona los informes
 * @property informeRepository InformeRepository
 */
class InformeController(
    private val informeRepository: InformeRepository
) {

    /**
     * Función que crea un informe con valores aleatorios
     * @param idCita Int
     * @param idTrabajador Int
     * @param idVehiculo Int
     * @param dniPropietario String
     * @return Result<Informe, InformeException>
     */
    fun hacerInforme(idCita: Int, idTrabajador: Int, idVehiculo: Int, dniPropietario: String): Informe {
        return Informe(
            id = 0,
            frenado = (1..10).random().toDouble(),
            frenadoIsApto = (0..1).random() == 1,
            contaminacion = (20..50).random().toDouble(),
            lucesIsApto = (0..1).random() == 1,
            idCita = idCita,
            idTrabajador = idTrabajador,
            idVehiculo = idVehiculo,
            dniPropietario = dniPropietario,
            isFavorable = (0..1).random()
        )
    }

    /**
     * Función que encuentra los informes favorables
     * @return Result<List<Informe>, InformeException>
     */
    fun findByIsFavorable(): Result<List<Informe>, InformeException> {
        logger.info { "Encontrando informes favorables" }
        val informes = informeRepository.findByIsFavorable()
        return if (informes.isNotEmpty()) {
            logger.info { "Informes encontrados: $informes" }
            informes.forEach { InformeCacheManager.cache.put(it.id.toLong(), it) }
            Ok(informes)
        } else {
            Err(InformeException.NoEncontrado("Informes no encontrados"))
        }
    }

    /**
     * Función que encuentra todos los informes
     * @return Result<List<Informe>, InformeException>
     */
    fun findAll(): Result<List<Informe>, InformeException> {
        logger.info { "Encontrando todos los informes" }
        val informes = informeRepository.findAll()
        return if (informes.isNotEmpty()) {
            logger.info { "Informes encontrados: $informes" }
            informes.forEach { InformeCacheManager.cache.put(it.id.toLong(), it) }
            Ok(informes)
        } else {
            Err(InformeException.NoEncontrado("Informes no encontrados"))
        }
    }

    /**
     * Función que encuentra un informe por id
     * @param id Long
     * @return Result<Informe, InformeException>
     */
    fun findById( id: Long): Result<Informe, InformeException> {
        logger.info { "Encontrando informe por id: $id" }
        val informe = informeRepository.findById(id)
        return if (informe != null) {
            logger.info { "Informe encontrado: $informe" }
            InformeCacheManager.cache.put(informe.id.toLong(), informe)
            Ok(informe)
        } else {
            Err(InformeException.NoEncontrado("Informe no encontrado"))
        }
    }

    /**
     * Función que guarda un informe
     * @param informe Informe
     * @return Result<Informe, InformeException>
     */
    fun save(informe: Informe): Result<Informe, InformeException> {
        informe.validar().onFailure { return Err(InformeException.NoValido(it.message)) }
            .onSuccess { logger.info { " ✅ El informe es válido, guardando informe: $informe" }}
        val informeGuardado = informeRepository.save(informe)
        return if (informeGuardado != null) {
            logger.info { "Informe guardado: $informeGuardado" }
            InformeCacheManager.cache.put(informeGuardado.id.toLong(), informeGuardado)
            Ok(informeGuardado)
        } else {
            Err(InformeException.NoGuardado("Informe no guardado"))
        }
    }

    /**
     * Función que actualiza un informe
     * @param informe Informe
     * @return Result<Informe, InformeException>
     */
    fun update(informe: Informe): Result<Informe, InformeException> {
        informe.validar().onFailure { return Err(InformeException.NoValido(it.message)) }
            .onSuccess { logger.info { " ✅ El informe es válido, actualizando informe: $informe" }}
        val informeActualizado = informeRepository.update(informe)
        return if (informeActualizado != null) {
            logger.info { "Informe actualizado: $informeActualizado" }
            InformeCacheManager.cache.invalidate(informe.id.toLong())
            InformeCacheManager.cache.put(informeActualizado.id.toLong(), informeActualizado)
            Ok(informeActualizado)
        } else {
            Err(InformeException.NoGuardado("Informe no actualizado"))
        }

    }

    /**
     * Función que borra un informe
     * @param informe Informe
     * @return Result<Boolean, InformeException>
     */
    fun delete(informe: Informe): Result<Boolean, InformeException> {
        logger.info { "Borrando informe: $informe" }
        val informeBorrado = informeRepository.delete(informe)
        return if (informeBorrado) {
            logger.info { "Informe borrado: $informeBorrado" }
            InformeCacheManager.cache.invalidate(informe.id.toLong())
            Ok(informeBorrado)
        } else {
            Err(InformeException.NoGuardado("Informe no borrado"))
        }
    }
}