package es.sergiop.pepilandaSpringJPA.services.trabajadorService

import es.sergiop.pepilandaSpringJPA.exceptions.TrabajadorException
import es.sergiop.pepilandaSpringJPA.models.Trabajador
import es.sergiop.pepilandaSpringJPA.repositories.trabajadorRepository.TrabajadorRepository
import kotlinx.coroutines.flow.Flow
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

private val logger = KotlinLogging.logger {}

/**
 * Clase TrabajadorServiceImpl que implementa la interfaz TrabajadorService
 * @property trabajadorRepository: TrabajadorRepository
 */
@Service
class TrabajadorServiceImpl
@Autowired constructor(
private val trabajadorRepository: TrabajadorRepository
) : TrabajadorService{

    /**
     * Función que devuelve todos los trabajadores de la base de datos
     * @return Flow<Trabajador>
     */
    override suspend fun findAll(): Flow<Trabajador> {
        logger.info { "Trabajador service - findAll()" }
        return trabajadorRepository.findAll()
    }

    /**
     * Función que devuelve un trabajador de la base de datos a partir de su id
     * @param id: Long
     * @return Trabajador?
     */
    override suspend fun findById(id: Long): Trabajador? {
        logger.info { "Trabajador service - findById() with id: $id" }
        return trabajadorRepository.findById(id)?: throw TrabajadorException.NoEncontrado("No se encontró el trabajador con id: $id")
    }

    /**
     * Función que guarda un trabajador en la base de datos
     * @param entity: Trabajador
     * @return Trabajador
     */
    override suspend fun save(entity: Trabajador): Trabajador {
        logger.info { "Trabajador service - save() with entity: $entity" }
        return trabajadorRepository.save(entity)
    }

    /**
     * Función que actualiza un trabajador en la base de datos
     * @param entity: Trabajador
     * @return Trabajador?
     */
    override suspend fun update(entity: Trabajador): Trabajador? {
        logger.info { "Trabajador service - update() with entity: $entity" }
        val trabajador = entity.id?.let { trabajadorRepository.findById(it) }
        if(trabajador != null) {
            return trabajadorRepository.save(entity)
        } else{
            throw TrabajadorException.NoEncontrado("No se encontró el trabajador con id: ${entity.id}")
        }
    }

    /**
     * Función que elimina un trabajador de la base de datos
     * @param entity: Trabajador
     * @return Boolean
     */
    override suspend fun delete(entity: Trabajador): Boolean {
        logger.info { "Trabajador service - delete() with entity: $entity" }
        val trabajador = entity.id?.let { trabajadorRepository.findById(it) }
        if(trabajador != null) {
            trabajadorRepository.delete(entity)
            return true
        } else{
            throw TrabajadorException.NoEncontrado("No se encontró el trabajador con id: ${entity.id}")
        }
    }

}