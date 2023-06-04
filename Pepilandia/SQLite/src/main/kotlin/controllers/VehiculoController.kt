package controllers

import com.github.michaelbull.result.*
import exceptions.VehiculoException
import models.Vehiculo
import mu.KotlinLogging
import repository.vehiculoRepository.VehiculoRepositoryImpl
import validators.validar

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

private val logger = KotlinLogging.logger {}

class VehiculoController( private val vehiculoRepository: VehiculoRepositoryImpl) {

    /**
     * Función que encuentra todos los vehículos por su modelo
     * @param modelo String
     * @return Result<List<Vehiculo>, VehiculoException>
     */
    fun findByModelo(modelo: String): Result<Vehiculo, VehiculoException> {
        logger.info { "Encontrando vehiculo por modelo: $modelo" }
        val vehiculo = vehiculoRepository.findByModelo(modelo)
        return if (vehiculo != null) {
            logger.info { "Vehiculo encontrado: $vehiculo" }
            Ok(vehiculo)
        } else {
            Err(VehiculoException.NoEncontrado("Vehiculo no encontrado"))
        }
    }

    /**
     * Función que encuentra todos los vehículos por su matrícula
     * @param matricula String
     * @return Result<List<Vehiculo>, VehiculoException>
     */
    fun findByMatricula(matricula: String): Result<Vehiculo, VehiculoException> {
        logger.info { "Encontrando vehiculo por matricula: $matricula" }
        val vehiculo = vehiculoRepository.findByMatricula(matricula)
        return if (vehiculo != null) {
            logger.info { "Vehiculo encontrado: $vehiculo" }
            Ok(vehiculo)
        } else {
            Err(VehiculoException.NoEncontrado("Vehiculo no encontrado"))
        }
    }

    /**
     * Función que encuentra todos los vehiculos por el dni del propietario
     * @param dni String
     * @return Result<List<Vehiculo>, VehiculoException>
     */
    fun findByDniPropietario(dni: String): Result<List<Vehiculo>, VehiculoException> {
        logger.info { "Encontrando vehiculos por dni del propietario: $dni" }
        val vehiculos = vehiculoRepository.findByDniPropietario(dni)
        return if (vehiculos.isNotEmpty()) {
            logger.info { "Vehiculos encontrados: $vehiculos" }
            Ok(vehiculos)
        } else {
            Err(VehiculoException.NoEncontrado("Vehiculos no encontrados"))
        }
    }

    /**
     * Función que encuentra todos los vehiculos
     * @return Result<List<Vehiculo>, VehiculoException>
     */
    fun findAll(): Result<List<Vehiculo>, VehiculoException> {
        logger.info { "Encontrando todos los vehiculos" }
        val vehiculos = vehiculoRepository.findAll()
        return if (vehiculos.isNotEmpty()) {
            logger.info { "Vehiculos encontrados: $vehiculos" }
            Ok(vehiculos)
        } else {
            Err(VehiculoException.NoEncontrado("Vehiculos no encontrados"))
        }
    }

    /**
     * Función que encuentra un vehiculo por su id
     * @param id Long
     * @return Result<Vehiculo, VehiculoException>
     */
    fun findById(id: Long): Result<Vehiculo, VehiculoException> {
        logger.info { "Encontrando vehiculo por id: $id" }
        val vehiculo = vehiculoRepository.findById(id)
        return if (vehiculo != null) {
            logger.info { "Vehiculo encontrado: $vehiculo" }
            Ok(vehiculo)
        } else {
            Err(VehiculoException.NoEncontrado("Vehiculo no encontrado"))
        }
    }

    /**
     * Función que guarda un vehiculo
     * @param vehiculo Vehiculo
     * @return Result<Vehiculo, VehiculoException>
     */
    fun save(vehiculo: Vehiculo): Result<Vehiculo, VehiculoException> {
        vehiculo.validar().onFailure { return Err(VehiculoException.NoValido(it.message)) }
            .onSuccess { logger.info { " ✅ El vehículo es válido, guardando vehiculo: $vehiculo" }}
        val vehiculoGuardado = vehiculoRepository.save(vehiculo)
        return if (vehiculoGuardado != null) {
            logger.info { "Vehiculo guardado: $vehiculoGuardado" }
            Ok(vehiculoGuardado)
        } else {
            Err(VehiculoException.NoGuardado("Vehiculo no guardado"))
        }
    }

    /**
     * Función que actualiza un vehiculo
     * @param vehiculo Vehiculo
     * @return Result<Vehiculo, VehiculoException>
     */
    fun update(vehiculo: Vehiculo): Result<Vehiculo, VehiculoException> {
        vehiculo.validar().onFailure { return Err(VehiculoException.NoValido(it.message)) }
            .onSuccess { logger.info { " ✅ El vehículo es válido, actualizando vehiculo: $vehiculo" }}
        val vehiculoActualizado = vehiculoRepository.update(vehiculo)
        return if (vehiculoActualizado != null) {
            logger.info { "Vehiculo actualizado: $vehiculoActualizado" }
            Ok(vehiculoActualizado)
        } else {
            Err(VehiculoException.NoGuardado("Vehiculo no actualizado"))
        }
    }

    /**
     * Función que elimina un vehiculo
     * @param vehiculo Vehiculo
     * @return Result<Boolean, VehiculoException>
     */
    fun delete(vehiculo: Vehiculo): Result<Boolean, VehiculoException> {
        logger.info { "Eliminando vehiculo: $vehiculo" }
        val vehiculoEliminado = vehiculoRepository.delete(vehiculo)
        return if (vehiculoEliminado) {
            logger.info { "Vehiculo eliminado: $vehiculoEliminado" }
            Ok(vehiculoEliminado)
        } else {
            Err(VehiculoException.NoGuardado("Vehiculo no eliminado"))
        }
    }

}