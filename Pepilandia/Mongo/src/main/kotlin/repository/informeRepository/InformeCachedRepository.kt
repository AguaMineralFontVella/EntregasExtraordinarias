package repository.informeRepository

import cache.informeCache.IInformeCache
import exceptions.InformeException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.withContext
import models.Informe

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Repositorio de la entidad Informe
 * @property informeCache IInformeCache
 */
class InformeCachedRepository(
    private val informeCache: IInformeCache
) : IInformeRepository{

    /**
     * Función que devuelve todos los informes
     * @return Todos los informes
     */
    override suspend fun findAll(): Flow<Informe> = withContext(Dispatchers.IO){
        return@withContext informeCache.cache.asMap().values.asFlow()
    }

    /**
     * Función que devuelve un informe por su id
     * @param id Long
     * @return Informe
     */
    override suspend fun findById(id: Long): Informe? = withContext(Dispatchers.IO){
        return@withContext informeCache.cache.get(id.toString())
    }

    /**
     * Función que guarda un informe
     * @param entity Informe
     * @return Informe guardado
     */
    override suspend fun save(entity: Informe): Informe = withContext(Dispatchers.IO){
        return@withContext try {
            informeCache.cache.put(entity.id,entity)
            entity
        } catch (e: Exception){
            throw InformeException.NoGuardado("No se ha podido guardar el informe: $entity")
        }
    }

    /**
     * Función que actualiza un informe
     * @param entity Informe
     * @return Informe actualizado
     */
    override suspend fun update(entity: Informe): Informe? = withContext(Dispatchers.IO){
        val cacheInforme = informeCache.cache.get(entity.id)
        if (cacheInforme != null) {
            val informeUpdate = cacheInforme.copy(
                id = entity.id,
                frenado = entity.frenado,
                frenadoIsApto = entity.frenadoIsApto,
                contaminacion = entity.contaminacion,
                lucesIsApto = entity.lucesIsApto,
                idCita = entity.idCita,
                idTrabajador = entity.idTrabajador,
                idVehiculo = entity.idVehiculo,
                dniPropietario = entity.dniPropietario,
                isFavorable = entity.isFavorable,
            )
            informeCache.cache.put(entity.id, informeUpdate)
        }
        return@withContext cacheInforme
    }

    /**
     * Función que elimina un informe
     * @param entity Informe
     * @return Boolean
     */
    override suspend fun delete(entity: Informe): Boolean = withContext(Dispatchers.IO){
        return@withContext try {
            informeCache.cache.invalidate(entity.id)
            true
        } catch (e: Exception){
            throw InformeException.NoGuardado("No se ha podido eliminar el informe: $entity")
        }
    }

}