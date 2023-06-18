package repository.citaRepository

import cache.citaCache.CitaCache
import exceptions.CitaException
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import models.Cita
import kotlinx.coroutines.flow.asFlow

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Clase CitaCachedRepository
 * @property cache
 */
class CitaCachedRepository (
    private val cache : CitaCache
) : ICitaRepository {

    /**
     * Función para buscar todas las citas
     * @return Flow<Cita>
     */
    override suspend fun findAll(): Flow<Cita> = withContext(Dispatchers.IO) {
        return@withContext cache.cache.asMap().values.asFlow<Cita>()
    }

    /**
     * Función para buscar una cita por su id
     * @param id
     * @return Cita?
     */
    override suspend fun findById(id: Long): Cita? = withContext(Dispatchers.IO) {
        return@withContext cache.cache.get(id.toString())
    }

    /**
     * Función para guardar una cita
     * @param entity
     * @return Cita
     */
    override suspend fun save(entity: Cita): Cita = withContext(Dispatchers.IO){
        return@withContext try{
            cache.cache.put(entity.id,entity)
            entity
        } catch (e: Exception){
            throw CitaException.NoGuardado("No se ha podido guardar la cita: $entity")
        }
    }

    /**
     * Función para actualizar una cita
     * @param entity
     * @return Cita?
     */
    override suspend fun update(entity: Cita): Cita? = withContext(Dispatchers.IO){
        val cacheCita = cache.cache.get(entity.id)
        if (cacheCita!=null){
            val citaUpdate = cacheCita.copy(
                id = entity.id,
                idTrabajador = entity.idTrabajador,
                idVehiculo = entity.idVehiculo,
                fecha = entity.fecha,
                hora = entity.hora,
            )
            cache.cache.put(entity.id,citaUpdate)
        }
        return@withContext entity
    }

    /**
     * Función para eliminar una cita
     * @param entity
     * @return Boolean
     */
    override suspend fun delete(entity: Cita): Boolean = withContext(Dispatchers.IO)  {
        return@withContext try{
            cache.cache.invalidate(entity.id)
            true
        } catch (e: Exception){
            throw CitaException.NoGuardado("No se ha podido eliminar la cita: $entity")
        }
    }

}