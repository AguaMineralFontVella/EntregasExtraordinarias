package controller

import com.github.michaelbull.result.onFailure
import exceptions.VehiculoException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.withContext
import models.Vehiculo
import repository.vehiculoRepository.IVehiculoRepository
import repository.vehiculoRepository.VehiculoCachedRepository
import validators.validar

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Controlador de los vehiculos
 * @param vehiculoRepository Repositorio de los vehiculos
 * @param vehiculoCache Caché de los vehiculos
 */
class VehiculoController(
    private val vehiculoRepository: IVehiculoRepository,
    private val vehiculoCache: VehiculoCachedRepository
    ) {

    /**
     * Función que devuelve todos los vehiculos
     * @return Flow de vehiculos
     */
    suspend fun findAllVehiculo() : Flow<Vehiculo>? = withContext(Dispatchers.IO){
        val vehiculos = vehiculoCache.findAll().toList()
        if (vehiculos.isEmpty()) {
            vehiculoRepository.findAll()
        } else {
            vehiculos.asFlow()
        }
    }

    /**
     * Función que devuelve un vehiculo por su id
     * @param id Id del vehiculo
     * @return Vehiculo
     */
    suspend fun findVehiculoById(id: Long): Vehiculo? = withContext(Dispatchers.IO){
        val vehiculo = vehiculoCache.findById(id)
        if (vehiculo == null) {
            vehiculoRepository.findById(id)
        } else {
            vehiculo
        }
    }

    /**
     * Función que guarda un vehiculo
     * @param vehiculo Vehiculo a guardar
     */
    suspend fun saveVehiculo(vehiculo: Vehiculo) = withContext(Dispatchers.IO){
        vehiculo.validar().onFailure { throw VehiculoException.NoValido("Error al validar el vehiculo, asegúrate de que todos los campos son correctos") }
        vehiculoRepository.save(vehiculo)
        vehiculoCache.save(vehiculo)
    }

    /**
     * Función que borra un vehiculo
     * @param vehiculo Vehiculo a borrar
     */
    suspend fun deleteVehiculo(vehiculo: Vehiculo): Boolean = withContext(Dispatchers.IO){
        try{
            vehiculoRepository.delete(vehiculo)
            vehiculoCache.delete(vehiculo)
            return@withContext true
        }
        catch (e: Exception){
            return@withContext false
        }
    }
}