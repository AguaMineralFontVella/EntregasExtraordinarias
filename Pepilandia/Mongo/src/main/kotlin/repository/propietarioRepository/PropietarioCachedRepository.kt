package repository.propietarioRepository

import cache.propietarioCache.IPropietarioCache
import dto.PropietarioCreateDto
import exceptions.PropietarioException
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import mappers.toPropietario
import models.Propietario
import org.litote.kmongo.Id

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Repositorio de la entidad Propietario
 * @property propietarioCache IPropietarioCache
 */
class PropietarioCachedRepository(
    private val propietarioCache: IPropietarioCache
): IPropietarioRepository {

    /**
     * Función que devuelve todos los propietarios
     * @return Todos los propietarios
     */
    override suspend fun findAll(): Flow<Propietario> = withContext(Dispatchers.IO) {
        return@withContext propietarioCache.cache.asMap().values.asFlow<Propietario>()
    }

    /**
     * Función que devuelve un propietario por su id
     * @param id Long
     * @return Propietario
     */
    override suspend fun findById(id: Long): Propietario? = withContext(Dispatchers.IO) {
        return@withContext propietarioCache.cache.get(id.toString())
    }

    /**
     * Función que guarda un propietario
     * @param entity Propietario
     * @return Propietario guardado
     */
    override suspend fun save(entity: Propietario): Propietario = withContext(Dispatchers.IO){
        return@withContext try {
            propietarioCache.cache.put(entity.id,entity)
            entity
        } catch (e: Exception){
            throw PropietarioException.NoGuardado("No se ha podido guardar el propietario: $entity")
        }
    }

    /**
     * Función que actualiza un propietario
     * @param entity Propietario
     * @return Propietario actualizado
     */
    override suspend fun update(entity: Propietario): Propietario? = withContext(Dispatchers.IO){
        val cachePropietario = propietarioCache.cache.get(entity.id)
        if (cachePropietario != null) {
            val propietarioUpdate = cachePropietario.copy(
                id = entity.id,
                dni = entity.dni,
                nombre = entity.nombre,
                apellidos = entity.apellidos,
                telefono = entity.telefono
            )
            propietarioCache.cache.put(entity.id, propietarioUpdate)
        }
        return@withContext entity
    }

    /**
     * Función que elimina un propietario
     * @param entity Propietario
     * @return Boolean
     */
    override suspend fun delete(entity: Propietario): Boolean = withContext(Dispatchers.IO) {
        return@withContext try {
            propietarioCache.cache.invalidate(entity.id)
            true
        } catch (e: Exception){
            throw PropietarioException.NoGuardado("No se ha podido eliminar el propietario: $entity")
        }
    }

}