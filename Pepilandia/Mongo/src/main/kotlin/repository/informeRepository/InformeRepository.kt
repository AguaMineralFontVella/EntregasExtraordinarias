package repository.informeRepository

import db.DataBaseManager
import exceptions.InformeException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.withContext
import models.Informe
import mu.KotlinLogging

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

private val logger = KotlinLogging.logger {}

/**
 * Repositorio de la entidad Informe
 */
class InformeRepository : IInformeRepository {

    /**
     * Función que devuelve todos los informes
     * @return Todos los informes
     */
    override suspend fun findAll(): Flow<Informe> = withContext(Dispatchers.IO) {
        return@withContext try{
            logger.info { "Informe repository - findAll()" }
            DataBaseManager.database.getCollection<Informe>().find().publisher.asFlow()
        } catch (e: Exception){
            throw InformeException.NoEncontrado("No se han encontrado informes")
        }
    }

    /**
     * Función que devuelve un informe por su id
     * @param id Long
     * @return Informe
     */
    override suspend fun findById(id: Long): Informe? = withContext(Dispatchers.IO) {
        return@withContext try{
            logger.info { "Informe repository - findById($id)" }
            DataBaseManager.database.getCollection<Informe>().findOneById(id)
        }
        catch (e: Exception){
            throw InformeException.NoEncontrado("No se ha encontrado el informe con id: $id")
        }
    }

    /**
     * Función que guarda un informe
     * @param entity Informe
     * @return Informe guardado
     */
    override suspend fun save(entity: Informe): Informe = withContext(Dispatchers.IO){
        return@withContext try{
            logger.info { "Informe repository - save($entity)" }
            DataBaseManager.database.getCollection<Informe>().insertOne(entity)
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
    override suspend fun update(entity: Informe): Informe? = withContext(Dispatchers.IO) {
        return@withContext try {
            logger.info { "Informe repository - update($entity)" }
            val informe = entity.id.let { DataBaseManager.database.getCollection<Informe>().findOneById(it) }
            if (informe != null) {
                DataBaseManager.database.getCollection<Informe>().updateOneById(entity.id, entity)
                entity
            } else {
                throw InformeException.NoEncontrado("No se ha encontrado el informe con id: ${entity.id}")
            }
        }
        catch (e: Exception){
            throw InformeException.NoGuardado("No se ha podido actualizar el informe: $entity")
        }
    }

    /**
     * Función que elimina un informe
     * @param entity Informe
     * @return Boolean
     */
    override suspend fun delete(entity: Informe): Boolean = withContext(Dispatchers.IO) {
        return@withContext try {
            logger.info { "Informe repository - delete($entity)" }
            val informe = entity.id.let { DataBaseManager.database.getCollection<Informe>().findOneById(it) }
            if (informe != null) {
                DataBaseManager.database.getCollection<Informe>().deleteOneById(entity.id)
                true
            } else {
                throw InformeException.NoEncontrado("No se ha encontrado el informe con id: ${entity.id}")
            }
        }
        catch (e: Exception){
            throw InformeException.NoGuardado("No se ha podido eliminar el informe: $entity")
        }
    }

}