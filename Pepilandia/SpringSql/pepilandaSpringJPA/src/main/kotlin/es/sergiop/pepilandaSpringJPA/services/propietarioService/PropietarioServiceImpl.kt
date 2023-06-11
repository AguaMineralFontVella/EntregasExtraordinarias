package es.sergiop.pepilandaSpringJPA.services.propietarioService

import es.sergiop.pepilandaSpringJPA.exceptions.PropietarioException
import es.sergiop.pepilandaSpringJPA.models.Propietario
import es.sergiop.pepilandaSpringJPA.repositories.propietarioRepository.PropietarioRepository
import kotlinx.coroutines.flow.Flow
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

@Service
class PropietarioServiceImpl
@Autowired constructor(
    private val propietarioRepository: PropietarioRepository
) : PropietarioService{

    override suspend fun findAll(): Flow<Propietario> {
        logger.info { "Propietario service - findAll()" }
        return propietarioRepository.findAll()
    }

    override suspend fun findById(id: Long): Propietario? {
        logger.info { "Propietario service - findById() with id: $id" }
        return propietarioRepository.findById(id)?: throw PropietarioException.NoEncontrado("No se encontró el propietario con id: $id")
    }

    override suspend fun save(entity: Propietario): Propietario {
        logger.info { "Propietario service - save() with entity: $entity" }
        return propietarioRepository.save(entity)
    }

    override suspend fun update(entity: Propietario): Propietario? {
        logger.info { "Propietario service - update() with entity: $entity" }
        val propietario = entity.id?.let { propietarioRepository.findById(it) }
        if(propietario != null) {
            return propietarioRepository.save(entity)
        } else{
            throw PropietarioException.NoEncontrado("No se encontró el propietario con id: ${entity.id}")
        }
    }

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