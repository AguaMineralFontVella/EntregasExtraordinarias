package repository.trabajadorRepository

import cache.trabajadorCache.ITrabajadorCache
import exceptions.TrabajadorException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.withContext
import models.Trabajador

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Repositorio de la entidad Trabajador
 * @property trabajadorCache ITrabajadorCache
 */
class TrabajadorCachedRepository(
    private val trabajadorCache: ITrabajadorCache
) : ITrabajadorRepository{

    /**
     * Función que devuelve todos los trabajadores
     * @return Todos los trabajadores
     */
    override suspend fun findAll(): Flow<Trabajador> = withContext(Dispatchers.IO) {
        return@withContext trabajadorCache.cache.asMap().values.asFlow<Trabajador>()
    }

    /**
     * Función que devuelve un trabajador por su id
     * @param id Long
     * @return Trabajador
     */
    override suspend fun findById(id: Long): Trabajador? = withContext(Dispatchers.IO) {
        return@withContext trabajadorCache.cache.get(id.toString())
    }

    /**
     * Función que guarda un trabajador
     * @param entity Trabajador
     * @return Trabajador guardado
     */
    override suspend fun save(entity: Trabajador): Trabajador = withContext(Dispatchers.IO){
        return@withContext try {
            trabajadorCache.cache.put(entity.id,entity)
            entity
        } catch (e: Exception){
            throw TrabajadorException.NoGuardado("No se ha podido guardar el trabajador: $entity")
        }
    }

    /**
     * Función que actualiza un trabajador
     * @param entity Trabajador
     * @return Trabajador actualizado
     */
    override suspend fun update(entity: Trabajador): Trabajador? = withContext(Dispatchers.IO){
        val cacheTrabajador = trabajadorCache.cache.get(entity.id)
        if (cacheTrabajador != null) {
            val trabajadorUpdate = cacheTrabajador.copy(
                id = entity.id,
                nombre = entity.nombre,
                telefono = entity.telefono,
                email = entity.email,
                nombreUsuario = entity.nombreUsuario,
                password = entity.password,
                especialidad = entity.especialidad,
                isResponsable = entity.isResponsable,
                salario = entity.salario,
                fechaContratacion = entity.fechaContratacion,
            )
            trabajadorCache.cache.put(entity.id, trabajadorUpdate)
        }
        return@withContext entity
    }

    /**
     * Función que elimina un trabajador
     * @param entity Trabajador
     * @return Boolean
     */
    override suspend fun delete(entity: Trabajador): Boolean = withContext(Dispatchers.IO) {
        return@withContext try {
            trabajadorCache.cache.invalidate(entity.id)
            true
        } catch (e: Exception){
            throw TrabajadorException.NoGuardado("No se ha podido eliminar el trabajador: $entity")
        }
    }

}