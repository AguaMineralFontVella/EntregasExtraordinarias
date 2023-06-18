package es.sergiop.pepilandaSpringJPA.services.informeService

import es.sergiop.pepilandaSpringJPA.exceptions.InformeException
import es.sergiop.pepilandaSpringJPA.models.Informe
import es.sergiop.pepilandaSpringJPA.repositories.informeRepository.InformeCachedRepositoryImpl
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
 * Clase InformeServiceImpl que implementa la interfaz InformeService
 * @property informeRepository: InformeCachedRepositoryImpl
 */
@Service
class InformeServiceImpl
@Autowired constructor(
    private val informeRepository: InformeCachedRepositoryImpl
) : InformeService {

    /**
     * Función que devuelve todos los informes de la base de datos
     * @return Flow<Informe>
     */
    override suspend fun findAll(): Flow<Informe> {
        logger.info { "Informe - findAll()" }
        return informeRepository.findAll()
    }

    /**
     * Función que devuelve un informe de la base de datos a partir de su id
     * @param id: Long
     * @return Informe?
     */
    override suspend fun findById(id: Long): Informe? {
        logger.info { "Informe - findById() with id: $id" }
        return informeRepository.findById(id)?: throw InformeException.NoEncontrado("Informe con id: $id no encontrado")
    }

    /**
     * Función que guarda un informe en la base de datos
     * @param entity: Informe
     * @return Informe
     */
    override suspend fun save(entity: Informe): Informe {
        logger.info { "Informe - save() Informe: $entity" }
        return informeRepository.save(entity)
    }

    /**
     * Función que actualiza un informe en la base de datos
     * @param entity: Informe
     * @return Informe?
     */
    override suspend fun update(entity: Informe): Informe? {
        logger.info { "Informe - update() Informe: $entity" }
        val encontrado = entity.id?.let { informeRepository.findById(it) }
        return encontrado?.let{
            informeRepository.update(entity)
        } ?: throw InformeException.NoEncontrado("Informe con id: ${entity.id} no encontrado")
    }

    /**
     * Función que elimina un informe de la base de datos
     * @param entity: Informe
     * @return Boolean
     */
    override suspend fun delete(entity: Informe): Boolean {
        logger.info { "Informe - delete() Informe: $entity" }
        val encontrado = entity.id?.let { informeRepository.findById(it) }
        encontrado?.let{
            informeRepository.delete(entity)
            return true
        } ?: throw InformeException.NoEncontrado("Informe con id: ${entity.id} no encontrado")
    }

}