package repository.propietarioRepository

import db.DataBaseManager
import dto.PropietarioCreateDto
import exceptions.PropietarioException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.withContext
import models.Propietario
import mu.KotlinLogging

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

private val logger = KotlinLogging.logger {}

/**
 * Repositorio de la entidad Propietario
 */
class PropietarioRepository : IPropietarioRepository{

    /**
     * Función que devuelve todos los propietarios
     * @return Todos los propietarios
     */
    override suspend fun findAll(): Flow<Propietario> = withContext(Dispatchers.IO) {
        return@withContext try{
            logger.info { "Propietario repository - findAll()" }
            DataBaseManager.database.getCollection<Propietario>().find().publisher.asFlow()
        } catch (e: Exception){
            throw PropietarioException.NoEncontrado("No se han encontrado propietarios")
        }
    }

    /**
     * Función que devuelve un propietario por su id
     * @param id Long
     * @return Propietario
     */
    override suspend fun findById(id: Long): Propietario? = withContext(Dispatchers.IO) {
        return@withContext try{
            logger.info { "Propietario repository - findById($id)" }
            DataBaseManager.database.getCollection<Propietario>().findOneById(id)
        }
        catch (e: Exception){
            throw PropietarioException.NoEncontrado("No se ha encontrado el propietario con id: $id")
        }
    }

    /**
     * Función que guarda un propietario
     * @param entity Propietario
     * @return Propietario guardado
     */
    override suspend fun save(entity: Propietario): Propietario = withContext(Dispatchers.IO){
        return@withContext try{
            logger.info { "Propietario repository - save($entity)" }
            DataBaseManager.database.getCollection<Propietario>().insertOne(entity)
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
        return@withContext try{
            logger.info { "Propietario repository - update($entity)" }
            val propietario = entity.id?.let { DataBaseManager.database.getCollection<Propietario>().findOneById(it) }
            if (propietario != null){
                DataBaseManager.database.getCollection<Propietario>().updateOneById(entity.id, entity)
                entity
            } else {
                throw PropietarioException.NoEncontrado("No se ha encontrado el propietario con id: ${entity.id}")
            }
        } catch (e: Exception){
            throw PropietarioException.NoGuardado("No se ha podido actualizar el propietario: $entity")
        }
    }

    /**
     * Función que elimina un propietario
     * @param entity Propietario
     * @return Boolean
     */
    override suspend fun delete(entity: Propietario): Boolean = withContext(Dispatchers.IO) {
        return@withContext try{
            logger.info { "Propietario repository - delete($entity)" }
            val propietario = entity.id?.let { DataBaseManager.database.getCollection<Propietario>().findOneById(it) }
            if (propietario != null){
                DataBaseManager.database.getCollection<Propietario>().deleteOneById(entity.id)
                true
            } else {
                throw PropietarioException.NoEncontrado("No se ha encontrado el propietario con id: ${entity.id}")
            }
        } catch (e: Exception){
            throw PropietarioException.NoGuardado("No se ha podido eliminar el propietario: $entity")
        }
    }

}