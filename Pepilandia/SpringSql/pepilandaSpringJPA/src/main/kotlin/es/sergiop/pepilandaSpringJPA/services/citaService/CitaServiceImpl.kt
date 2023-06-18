package es.sergiop.pepilandaSpringJPA.services.citaService

import es.sergiop.pepilandaSpringJPA.exceptions.CitaException
import es.sergiop.pepilandaSpringJPA.models.Cita
import es.sergiop.pepilandaSpringJPA.repositories.citaRepository.CitaCachedRepositoryImpl
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
 * Clase CitaServiceImpl que implementa la interfaz CitaService
 * @property citaRepository: CitaCachedRepositoryImpl
 */
@Service
class CitaServiceImpl
@Autowired constructor(
    private val citaRepository: CitaCachedRepositoryImpl
) : CitaService {

    /**
     * Función que devuelve todas las citas de la base de datos
     * @return Flow<Cita>
     */
    override suspend fun findAll(): Flow<Cita> {
        logger.info { "Cita - findAll()" }
        return citaRepository.findAll()
    }

    /**
     * Función que devuelve una cita de la base de datos a partir de su id
     * @param id: Long
     * @return Cita?
     */
    override suspend fun findById(id: Long): Cita? {
        logger.info { "Cita - findById() with id: $id" }
        return citaRepository.findById(id)?: throw CitaException.NoEncontrado("Cita con id: $id no encontrada")
    }

    /**
     * Función que guarda una cita en la base de datos
     * @param entity: Cita
     * @return Cita
     */
    override suspend fun save(entity: Cita): Cita {
        logger.info { "Cita - save() Cita: $entity" }
        return citaRepository.save(entity)
    }

    /**
     * Función que actualiza una cita en la base de datos
     * @param entity: Cita
     * @return Cita?
     */
    override suspend fun update(entity: Cita): Cita? {
        logger.info { "Cita - update() Cita: $entity" }
        val encontrada = entity.id?.let { citaRepository.findById(it) }
        return encontrada?.let{
            citaRepository.update(entity)
        } ?: throw CitaException.NoEncontrado("Cita con id: ${entity.id} no encontrada")
    }

    /**
     * Función que borra una cita de la base de datos
     * @param entity: Cita
     * @return Boolean
     */
    override suspend fun delete(entity: Cita): Boolean {
        logger.info { "Cita - delete() Cita: $entity" }
        val encontrada = entity.id?.let { citaRepository.findById(it) }
        encontrada?.let{
            citaRepository.delete(entity)
            return true
        } ?: throw CitaException.NoEncontrado("Cita con id: ${entity.id} no encontrada")
    }

}