package es.sergiop.pepilandaSpringJPA.services.vehiculoService

import es.sergiop.pepilandaSpringJPA.exceptions.VehiculoException
import es.sergiop.pepilandaSpringJPA.models.Vehiculo
import es.sergiop.pepilandaSpringJPA.repositories.vehiculoRepository.VehiculoRepository
import kotlinx.coroutines.flow.Flow
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

@Service
class VehiculoServiceImpl
@Autowired constructor(
    private val vehiculoRepository: VehiculoRepository
) : VehiculoService{

    override suspend fun findAll(): Flow<Vehiculo> {
        logger.info { "Vehiculo service - findAll()" }
        return vehiculoRepository.findAll()
    }

    override suspend fun findById(id: Long): Vehiculo? {
        logger.info { "Vehiculo service - findById() with id: $id" }
        return vehiculoRepository.findById(id)?: throw VehiculoException.NoEncontrado("No se encontró el vehiculo con id: $id")
    }

    override suspend fun save(entity: Vehiculo): Vehiculo {
        logger.info { "Vehiculo service - save() with entity: $entity" }
        return vehiculoRepository.save(entity)
    }

    override suspend fun update(entity: Vehiculo): Vehiculo? {
        logger.info { "Vehiculo service - update() with entity: $entity" }
        val vehiculo = entity.id?.let { vehiculoRepository.findById(it) }
        if(vehiculo != null) {
            return vehiculoRepository.save(entity)
        } else{
            throw VehiculoException.NoEncontrado("No se encontró el vehiculo con id: ${entity.id}")
        }
    }

    override suspend fun delete(entity: Vehiculo): Boolean {
        logger.info { "Vehiculo service - delete() with entity: $entity" }
        val vehiculo = entity.id?.let { vehiculoRepository.findById(it) }
        if(vehiculo != null) {
            vehiculoRepository.delete(entity)
            return true
        } else{
            throw VehiculoException.NoEncontrado("No se encontró el vehiculo con id: ${entity.id}")
        }
    }

}