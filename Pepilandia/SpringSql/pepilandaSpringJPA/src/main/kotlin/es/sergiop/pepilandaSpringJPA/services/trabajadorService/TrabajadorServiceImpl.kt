package es.sergiop.pepilandaSpringJPA.services.trabajadorService

import es.sergiop.pepilandaSpringJPA.exceptions.TrabajadorException
import es.sergiop.pepilandaSpringJPA.models.Trabajador
import es.sergiop.pepilandaSpringJPA.repositories.trabajadorRepository.TrabajadorRepository
import kotlinx.coroutines.flow.Flow
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

@Service
class TrabajadorServiceImpl
@Autowired constructor(
private val trabajadorRepository: TrabajadorRepository
) : TrabajadorService{

    override suspend fun findAll(): Flow<Trabajador> {
        logger.info { "Trabajador service - findAll()" }
        return trabajadorRepository.findAll()
    }

    override suspend fun findById(id: Long): Trabajador? {
        logger.info { "Trabajador service - findById() with id: $id" }
        return trabajadorRepository.findById(id)?: throw TrabajadorException.NoEncontrado("No se encontró el trabajador con id: $id")
    }

    override suspend fun save(entity: Trabajador): Trabajador {
        logger.info { "Trabajador service - save() with entity: $entity" }
        return trabajadorRepository.save(entity)
    }

    override suspend fun update(entity: Trabajador): Trabajador? {
        logger.info { "Trabajador service - update() with entity: $entity" }
        val trabajador = entity.id?.let { trabajadorRepository.findById(it) }
        if(trabajador != null) {
            return trabajadorRepository.save(entity)
        } else{
            throw TrabajadorException.NoEncontrado("No se encontró el trabajador con id: ${entity.id}")
        }
    }

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