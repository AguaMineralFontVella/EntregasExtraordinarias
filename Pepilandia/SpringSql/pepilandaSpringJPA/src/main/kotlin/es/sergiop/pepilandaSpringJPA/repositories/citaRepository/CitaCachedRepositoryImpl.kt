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

private val logger = KotlinLogging.logger {}

@Repository
class CitaCachedRepositoryImpl
@Autowired constructor(
    private val citaRepository: CitaRepository
) : CitaCachedRepository {

    override suspend fun findAll(): Flow<Cita> = withContext(Dispatchers.IO) {
        logger.info { "Cached cita - findAll()" }
        return@withContext citaRepository.findAll()
    }

    @Cacheable("citas")
    override suspend fun findById(id: Long): Cita? = withContext(Dispatchers.IO) {
        logger.info { "Cached cita - findById() with id: $id" }
        return@withContext citaRepository.findById(id)
    }

    @CachePut("citas")
    override suspend fun save(entity: Cita): Cita = withContext(Dispatchers.IO) {
        logger.info { "Cached cita - save() Cita: $entity" }
        return@withContext citaRepository.save(entity)
    }

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