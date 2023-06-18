package repository.citaRepository

import db.DataBaseManager
import exceptions.CitaException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.withContext
import models.Cita
import mu.KotlinLogging

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

private val logger = KotlinLogging.logger {}

/**
 * Clase CitaRepository
 * @property DataBaseManager
 */
class CitaRepository: ICitaRepository {

    /**
     * Función para buscar todas las citas
     * @return Flow<Cita>
     */
    override suspend fun findAll(): Flow<Cita> = withContext(Dispatchers.IO){
        return@withContext try{
            logger.info { "Cita repository - findAll()" }
            DataBaseManager.database.getCollection<Cita>().find().publisher.asFlow()
        } catch (e: Exception){
            throw CitaException.NoEncontrado("No se han encontrado citas")
        }
    }

    /**
     * Función para buscar una cita por su id
     * @param id
     * @return Cita?
     */
    override suspend fun findById(id: Long): Cita? = withContext(Dispatchers.IO){
        return@withContext try{
            logger.info { "Cita repository - findById($id)" }
            DataBaseManager.database.getCollection<Cita>().findOneById(id)
        }
        catch (e: Exception){
            throw CitaException.NoEncontrado("No se ha encontrado la cita con id: $id")
        }
    }

    /**
     * Función para guardar una cita
     * @param entity
     * @return Cita
     */
    override suspend fun save(entity: Cita): Cita = withContext(Dispatchers.IO){
        return@withContext try{
            logger.info { "Cita repository - save($entity)" }
            DataBaseManager.database.getCollection<Cita>().insertOne(entity)
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
        return@withContext try{
            logger.info { "Cita repository - update($entity)" }
            val cita = entity.id.let { DataBaseManager.database.getCollection<Cita>().findOneById(it) }
            if (cita != null){
                DataBaseManager.database.getCollection<Cita>().updateOneById(entity.id, entity)
                entity
            } else {
                throw CitaException.NoEncontrado("No se ha encontrado la cita con id: ${entity.id}")
            }
        } catch (e: Exception){
            throw CitaException.NoGuardado("No se ha podido actualizar la cita: $entity")
        }
    }

    /**
     * Función para borrar una cita
     * @param entity
     * @return Boolean
     */
    override suspend fun delete(entity: Cita): Boolean = withContext(Dispatchers.IO){
        return@withContext try{
            logger.info { "Cita repository - delete($entity)" }
            val cita = entity.id?.let { DataBaseManager.database.getCollection<Cita>().findOneById(it) }
            if (cita != null){
                DataBaseManager.database.getCollection<Cita>().deleteOneById(entity.id)
                true
            } else {
                throw CitaException.NoEncontrado("No se ha encontrado la cita con id: ${entity.id}")
            }
        } catch (e: Exception){
            throw CitaException.NoGuardado("No se ha podido borrar la cita: $entity")
        }
    }

}