package repository.vehiculoRepository

import db.DataBaseManager
import exceptions.VehiculoException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.withContext
import models.Vehiculo
import mu.KotlinLogging

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

private val logger = KotlinLogging.logger {}

/**
 * Repositorio de la entidad Vehículo
 */
class VehiculoRepository : IVehiculoRepository{

    /**
     * Función que devuelve todos los vehiculos
     * @return Todos los vehiculos
     */
    override suspend fun findAll(): Flow<Vehiculo> = withContext(Dispatchers.IO) {
        return@withContext try {
            logger.info { "Vehiculo repository - findAll()" }
            DataBaseManager.database.getCollection<Vehiculo>().find().publisher.asFlow()
        } catch (e: Exception) {
            throw Exception("No se han encontrado vehiculos")
        }
    }

    /**
     * Función que devuelve un vehiculo por su id
     * @param id Long
     * @return Vehiculo
     */
    override suspend fun findById(id: Long): Vehiculo? = withContext(Dispatchers.IO) {
        return@withContext try {
            logger.info { "Vehiculo repository - findById($id)" }
            DataBaseManager.database.getCollection<Vehiculo>().findOneById(id)
        }
        catch (e: Exception) {
            throw VehiculoException.NoEncontrado("No se ha encontrado el vehiculo con id: $id")
        }
    }

    /**
     * Función que guarda un vehiculo
     * @param entity Vehiculo
     * @return Vehiculo guardado
     */
    override suspend fun save(entity: Vehiculo): Vehiculo = withContext(Dispatchers.IO){
        return@withContext try {
            logger.info { "Vehiculo repository - save($entity)" }
            DataBaseManager.database.getCollection<Vehiculo>().insertOne(entity)
            entity
        } catch (e: Exception) {
            throw VehiculoException.NoGuardado("No se ha podido guardar el vehiculo: $entity")
        }
    }

    /**
     * Función que actualiza un vehiculo
     * @param entity Vehiculo
     * @return Vehiculo actualizado
     */
    override suspend fun update(entity: Vehiculo): Vehiculo? = withContext(Dispatchers.IO){
        return@withContext try {
            logger.info { "Vehiculo repository - update($entity)" }
            val vehiculo = entity.id.let { DataBaseManager.database.getCollection<Vehiculo>().findOneById(it) }
            if (vehiculo != null) {
                DataBaseManager.database.getCollection<Vehiculo>().updateOneById(entity.id, entity)
                entity
            } else {
                throw VehiculoException.NoEncontrado("No se ha encontrado el vehiculo con id: ${entity.id}")
            }
        } catch (e: Exception) {
            throw VehiculoException.NoGuardado("No se ha podido actualizar el vehiculo: $entity")
        }
    }

    /**
     * Función que elimina un vehiculo
     * @param entity Vehiculo
     * @return Boolean
     */
    override suspend fun delete(entity: Vehiculo): Boolean = withContext(Dispatchers.IO) {
        return@withContext try {
            logger.info { "Vehiculo repository - delete($entity)" }
            val vehiculo = entity.id.let { DataBaseManager.database.getCollection<Vehiculo>().findOneById(it) }
            if (vehiculo != null) {
                DataBaseManager.database.getCollection<Vehiculo>().deleteOneById(entity.id)
                true
            } else {
                throw VehiculoException.NoEncontrado("No se ha encontrado el vehiculo con id: ${entity.id}")
            }
        } catch (e: Exception) {
            throw VehiculoException.NoGuardado("No se ha podido eliminar el vehiculo: $entity")
        }
    }

}