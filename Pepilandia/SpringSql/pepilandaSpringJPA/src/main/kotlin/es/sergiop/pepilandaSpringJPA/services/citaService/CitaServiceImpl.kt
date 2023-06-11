package es.sergiop.pepilandaSpringJPA.services.citaService

import es.sergiop.pepilandaSpringJPA.exceptions.CitaException
import es.sergiop.pepilandaSpringJPA.models.Cita
import es.sergiop.pepilandaSpringJPA.repositories.citaRepository.CitaCachedRepositoryImpl
import kotlinx.coroutines.flow.Flow
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

@Service
class CitaServiceImpl
@Autowired constructor(
    private val citaRepository: CitaCachedRepositoryImpl
) : CitaService {

    override suspend fun findAll(): Flow<Cita> {
        logger.info { "Cita - findAll()" }
        return citaRepository.findAll()
    }

    override suspend fun findById(id: Long): Cita? {
        logger.info { "Cita - findById() with id: $id" }
        return citaRepository.findById(id)?: throw CitaException.NoEncontrado("Cita con id: $id no encontrada")
    }

    override suspend fun save(entity: Cita): Cita {
        logger.info { "Cita - save() Cita: $entity" }
        return citaRepository.save(entity)
    }

    override suspend fun update(entity: Cita): Cita? {
        logger.info { "Cita - update() Cita: $entity" }
        val encontrada = entity.id?.let { citaRepository.findById(it) }
        return encontrada?.let{
            citaRepository.update(entity)
        } ?: throw CitaException.NoEncontrado("Cita con id: ${entity.id} no encontrada")
    }

    override suspend fun delete(entity: Cita): Boolean {
        logger.info { "Cita - delete() Cita: $entity" }
        val encontrada = entity.id?.let { citaRepository.findById(it) }
        encontrada?.let{
            citaRepository.delete(entity)
            return true
        } ?: throw CitaException.NoEncontrado("Cita con id: ${entity.id} no encontrada")
    }

}