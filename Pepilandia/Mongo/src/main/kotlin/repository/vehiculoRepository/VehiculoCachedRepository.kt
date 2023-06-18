package repository.vehiculoRepository

import cache.vehiculoCache.IVehiculoCache
import dto.VehiculoCreateDto
import exceptions.VehiculoException
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import models.Vehiculo
import mappers.toVehiculo
import org.litote.kmongo.Id

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Repositorio de la entidad Vehículo
 * @property vehiculoCache IVehiculoCache
 */
class VehiculoCachedRepository (
    private val vehiculoCache: IVehiculoCache
) : IVehiculoRepository{

    /**
     * Función que devuelve todos los vehículos
     * @return Todos los vehículos
     */
    override suspend fun findAll(): Flow<Vehiculo> = withContext(Dispatchers.IO) {
        return@withContext vehiculoCache.cache.asMap().values.asFlow<Vehiculo>()
    }

    /**
     * Función que devuelve un vehículo por su id
     * @param id Long
     * @return Vehículo
     */
    override suspend fun findById(id: Long): Vehiculo? = withContext(Dispatchers.IO) {
        return@withContext vehiculoCache.cache.get(id.toString())
    }

    /**
     * Función que guarda un vehículo
     * @param entity Vehículo
     * @return Vehículo guardado
     */
    override suspend fun save(entity: Vehiculo): Vehiculo = withContext(Dispatchers.IO){
        return@withContext try {
            vehiculoCache.cache.put(entity.id,entity)
            entity
        } catch (e: Exception){
            throw VehiculoException.NoGuardado("No se ha podido guardar el vehiculo: $entity")
        }
    }

    /**
     * Función que actualiza un vehículo
     * @param entity Vehículo
     * @return Vehículo actualizado
     */
    override suspend fun update(entity: Vehiculo): Vehiculo? = withContext(Dispatchers.IO){
        val cacheVehiculo = vehiculoCache.cache.get(entity.id)
        if (cacheVehiculo != null) {
            val vehiculoUpdate = cacheVehiculo.copy(
                id = entity.id,
                marca = entity.marca,
                modelo = entity.modelo,
                matricula = entity.matricula,
                fechaMatriculacion = entity.fechaMatriculacion,
                fechaUltimaRevision = entity.fechaUltimaRevision,
                dniPropietario = entity.dniPropietario
            )
            vehiculoCache.cache.put(entity.id, vehiculoUpdate)
        }
        return@withContext entity
    }

    /**
     * Función que elimina un vehículo
     * @param entity Vehículo
     * @return Boolean
     */
    override suspend fun delete(entity: Vehiculo): Boolean = withContext(Dispatchers.IO) {
        return@withContext try {
            vehiculoCache.cache.invalidate(entity.id)
            true
        } catch (e: Exception){
            throw VehiculoException.NoGuardado("No se ha podido eliminar el vehiculo: $entity")
        }
    }

}