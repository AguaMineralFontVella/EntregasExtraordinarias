package repository.trabajadorRepository

import db.DataBaseManager
import exceptions.TrabajadorException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.withContext
import models.Trabajador
import mu.KotlinLogging

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

private val logger = KotlinLogging.logger {}

/**
 * Repositorio de la entidad Trabajador
 */
class TrabajadorRepository : ITrabajadorRepository{

    /**
     * Función que devuelve todos los trabajadores
     * @return Todos los trabajadores
     */
    override suspend fun findAll(): Flow<Trabajador> = withContext(Dispatchers.IO) {
        return@withContext try{
            logger.info { "Trabajador repository - findAll()" }
            DataBaseManager.database.getCollection<Trabajador>().find().publisher.asFlow()
        } catch (e: Exception){
            throw TrabajadorException.NoEncontrado("No se han encontrado trabajadores")
        }
    }

    /**
     * Función que devuelve un trabajador por su id
     * @param id Long
     * @return Trabajador
     */
    override suspend fun findById(id: Long): Trabajador? = withContext(Dispatchers.IO) {
        return@withContext try{
            logger.info { "Trabajador repository - findById($id)" }
            DataBaseManager.database.getCollection<Trabajador>().findOneById(id)
        }
        catch (e: Exception){
            throw TrabajadorException.NoEncontrado("No se ha encontrado el trabajador con id: $id")
        }
    }

    /**
     * Función que guarda un trabajador
     * @param entity Trabajador
     * @return Trabajador guardado
     */
    override suspend fun save(entity: Trabajador): Trabajador = withContext(Dispatchers.IO){
        return@withContext try{
            logger.info { "Trabajador repository - save($entity)" }
            DataBaseManager.database.getCollection<Trabajador>().insertOne(entity)
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
        return@withContext try{
            logger.info { "Trabajador repository - update($entity)" }
            val trabajador = entity.id.let { DataBaseManager.database.getCollection<Trabajador>().findOneById(it) }
            if (trabajador != null){
                DataBaseManager.database.getCollection<Trabajador>().updateOneById(entity.id, entity)
                entity
            } else {
                throw TrabajadorException.NoEncontrado("No se ha encontrado el trabajador con id: ${entity.id}")
            }
        } catch (e: Exception){
            throw TrabajadorException.NoGuardado("No se ha podido actualizar el trabajador: $entity")
        }
    }

    /**
     * Función que elimina un trabajador
     * @param entity Trabajador
     * @return Boolean
     */
    override suspend fun delete(entity: Trabajador): Boolean = withContext(Dispatchers.IO) {
        return@withContext try{
            logger.info { "Trabajador repository - delete($entity)" }
            val trabajador = entity.id?.let { DataBaseManager.database.getCollection<Trabajador>().findOneById(it) }
            if (trabajador != null){
                DataBaseManager.database.getCollection<Trabajador>().deleteOneById(entity.id)
                true
            } else {
                throw TrabajadorException.NoEncontrado("No se ha encontrado el trabajador con id: ${entity.id}")
            }
        } catch (e: Exception){
            throw TrabajadorException.NoGuardado("No se ha podido eliminar el trabajador: $entity")
        }
    }

}