package es.sergiop.pepilandaSpringJPA.repositories.citaRepository

import es.sergiop.pepilandaSpringJPA.exceptions.CitaException
import es.sergiop.pepilandaSpringJPA.models.Cita
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Repository

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

private val logger = KotlinLogging.logger {}

/**
 * Clase CitaCachedRepositoryImpl que se encarga de la cache de las citas en la base de datos
 * @property citaRepository: CitaRepository
 */
@Repository
class CitaCachedRepositoryImpl
@Autowired constructor(
    private val citaRepository: CitaRepository
) : CitaCachedRepository {

    /**
     * Función que devuelve todas las citas de la base de datos
     * @return Flow<Cita>
     */
    override suspend fun findAll(): Flow<Cita> = withContext(Dispatchers.IO) {
        logger.info { "Cached cita - findAll()" }
        return@withContext citaRepository.findAll()
    }

    /**
     * Función que devuelve una cita de la base de datos a partir de su id
     * @param id: Long
     * @return Cita?
     */
    @Cacheable("citas")
    override suspend fun findById(id: Long): Cita? = withContext(Dispatchers.IO) {
        logger.info { "Cached cita - findById() with id: $id" }
        return@withContext citaRepository.findById(id)
    }

    /**
     * Función que guarda una cita en la base de datos
     * @param entity: Cita
     * @return Cita
     */
    @CachePut("citas")
    override suspend fun save(entity: Cita): Cita = withContext(Dispatchers.IO) {
        logger.info { "Cached cita - save() Cita: $entity" }
        return@withContext citaRepository.save(entity)
    }

    /**
     * Función que actualiza una cita de la base de datos
     * @param entity: Cita
     * @return Cita?
     */
    @CachePut("citas")
    override suspend fun update(entity: Cita): Cita? = withContext(Dispatchers.IO){
        logger.info { "Cached cita - update() Cita: $entity" }
        val cita = entity.id?.let { citaRepository.findById(it) }
        cita?.let{
            val citaActualizada = it.copy(
                idTrabajador = entity.idTrabajador,
                idVehiculo = entity.idVehiculo,
                fecha = entity.fecha,
                hora = entity.hora,
            )
            return@withContext citaRepository.save(citaActualizada)
        }
        return@withContext null
    }

    /**
     * Función que elimina una cita de la base de datos
     * @param entity: Cita
     * @return Boolean
     */
    @CacheEvict("citas")
    override suspend fun delete(entity: Cita): Boolean = withContext(Dispatchers.IO){
        logger.info { "Cached cita - delete() Cita: $entity" }
        val cita = entity.id?.let { citaRepository.findById(it) }
        try{
            cita?.let{
                citaRepository.delete(it)
                return@withContext true
            }
        }
        catch (e: Exception){
            throw CitaException.NoGuardado("No se ha podido eliminar la cita")
        }
        return@withContext false
    }

}