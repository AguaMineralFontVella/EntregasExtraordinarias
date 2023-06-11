package es.sergiop.pepilandaSpringJPA.repositories.informeRepository

import es.sergiop.pepilandaSpringJPA.exceptions.InformeException
import es.sergiop.pepilandaSpringJPA.models.Informe
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
class InformeCachedRepositoryImpl
@Autowired constructor(
    private val informeRepository: InformeRepository
) : InformeCachedRepository {

    override suspend fun findAll(): Flow<Informe> = withContext(Dispatchers.IO) {
        logger.info { "Cached informe - findAll()" }
        return@withContext informeRepository.findAll()
    }

    @Cacheable("informes")
    override suspend fun findById(id: Long): Informe? = withContext(Dispatchers.IO) {
        logger.info { "Cached informe - findById() with id: $id" }
        return@withContext informeRepository.findById(id)
    }

    @CachePut("informes")
    override suspend fun save(entity: Informe): Informe = withContext(Dispatchers.IO) {
        logger.info { "Cached informe - save() Informe: $entity" }
        return@withContext informeRepository.save(entity)
    }

    @CachePut("informes")
    override suspend fun update(entity: Informe): Informe? = withContext(Dispatchers.IO) {
        logger.info { "Cached informe - update() Informe: $entity" }
        val informe = entity.id?.let { informeRepository.findById(it) }
        informe?.let{
            val informeActualizado = it.copy(
                frenado = entity.frenado,
                frenadoIsApto = entity.frenadoIsApto,
                contaminacion = entity.contaminacion,
                lucesIsApto = entity.lucesIsApto,
                idCita = entity.idCita,
                idTrabajador = entity.idTrabajador,
                idVehiculo = entity.idVehiculo,
                dniPropietario = entity.dniPropietario,
                isFavorable = entity.isFavorable,
            )
            return@withContext informeRepository.save(informeActualizado)
        }
        return@withContext null
    }

    @CacheEvict("informes")
    override suspend fun delete(entity: Informe): Boolean = withContext(Dispatchers.IO) {
        logger.info { "Cached informe - delete() Informe: $entity" }
        val informe = entity.id?.let { informeRepository.findById(it) }
        try{
            informe?.let {
                informeRepository.delete(it)
                return@withContext true
            }
        }
        catch (e: Exception){
            throw InformeException.NoGuardado("No se ha podido eliminar el informe")
        }
        return@withContext false
    }


}