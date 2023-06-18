package es.sergiop.pepilandaSpringJPA.services.propietarioService

import es.sergiop.pepilandaSpringJPA.exceptions.PropietarioException
import es.sergiop.pepilandaSpringJPA.models.Propietario
import es.sergiop.pepilandaSpringJPA.repositories.propietarioRepository.PropietarioRepository
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
 * Clase PropietarioServiceImpl que implementa la interfaz PropietarioService
 * @property propietarioRepository: PropietarioRepository
 */
@Service
class PropietarioServiceImpl
@Autowired constructor(
    private val propietarioRepository: PropietarioRepository
) : PropietarioService{

    /**
     * Función que devuelve todos los propietarios de la base de datos
     * @return Flow<Propietario>
     */
    override suspend fun findAll(): Flow<Propietario> {
        logger.info { "Propietario service - findAll()" }
        return propietarioRepository.findAll()
    }

    /**
     * Función que devuelve un propietario de la base de datos a partir de su id
     * @param id: Long
     * @return Propietario?
     */
    override suspend fun findById(id: Long): Propietario? {
        logger.info { "Propietario service - findById() with id: $id" }
        return propietarioRepository.findById(id)?: throw PropietarioException.NoEncontrado("No se encontró el propietario con id: $id")
    }

    /**
     * Función que guarda un propietario en la base de datos
     * @param entity: Propietario
     * @return Propietario
     */
    override suspend fun save(entity: Propietario): Propietario {
        logger.info { "Propietario service - save() with entity: $entity" }
        return propietarioRepository.save(entity)
    }

    /**
     * Función que actualiza un propietario en la base de datos
     * @param entity: Propietario
     * @return Propietario?
     */
    override suspend fun update(entity: Propietario): Propietario? {
        logger.info { "Propietario service - update() with entity: $entity" }
        val propietario = entity.id?.let { propietarioRepository.findById(it) }
        if(propietario != null) {
            return propietarioRepository.save(entity)
        } else{
            throw PropietarioException.NoEncontrado("No se encontró el propietario con id: ${entity.id}")
        }
    }

    /**
     * Función que elimina un propietario de la base de datos
     * @param entity: Propietario
     * @return Boolean
     */
    override suspend fun delete(entity: Propietario): Boolean {
        logger.info { "Propietario service - delete() with entity: $entity" }
        val propietario = entity.id?.let { propietarioRepository.findById(it) }
        if(propietario != null) {
            propietarioRepository.delete(entity)
            return true
        } else{
            throw PropietarioException.NoEncontrado("No se encontró el propietario con id: ${entity.id}")
        }
    }

}