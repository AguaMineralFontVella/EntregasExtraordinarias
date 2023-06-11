package es.sergiop.pepilandaSpringJPA.services.informeService

import es.sergiop.pepilandaSpringJPA.exceptions.InformeException
import es.sergiop.pepilandaSpringJPA.models.Informe
import es.sergiop.pepilandaSpringJPA.repositories.informeRepository.InformeCachedRepositoryImpl
import kotlinx.coroutines.flow.Flow
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

@Service
class InformeServiceImpl
@Autowired constructor(
    private val informeRepository: InformeCachedRepositoryImpl
) : InformeService {

    override suspend fun findAll(): Flow<Informe> {
        logger.info { "Informe - findAll()" }
        return informeRepository.findAll()
    }

    override suspend fun findById(id: Long): Informe? {
        logger.info { "Informe - findById() with id: $id" }
        return informeRepository.findById(id)?: throw InformeException.NoEncontrado("Informe con id: $id no encontrado")
    }

    override suspend fun save(entity: Informe): Informe {
        logger.info { "Informe - save() Informe: $entity" }
        return informeRepository.save(entity)
    }

    override suspend fun update(entity: Informe): Informe? {
        logger.info { "Informe - update() Informe: $entity" }
        val encontrado = entity.id?.let { informeRepository.findById(it) }
        return encontrado?.let{
            informeRepository.update(entity)
        } ?: throw InformeException.NoEncontrado("Informe con id: ${entity.id} no encontrado")
    }

    override suspend fun delete(entity: Informe): Boolean {
        logger.info { "Informe - delete() Informe: $entity" }
        val encontrado = entity.id?.let { informeRepository.findById(it) }
        encontrado?.let{
            informeRepository.delete(entity)
            return true
        } ?: throw InformeException.NoEncontrado("Informe con id: ${entity.id} no encontrado")
    }

}