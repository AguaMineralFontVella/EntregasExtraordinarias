package es.sergiop.reintentoextra.controller

import es.sergiop.reintentoextra.models.Planeta
import es.sergiop.reintentoextra.repositories.planetaRepository.PlanetaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

class PlanetasController (
    private val planetaRepository: PlanetaRepository
){

    //PLANETAS
    suspend fun planetasGetAll() : Flow<Planeta> = withContext(Dispatchers.IO){
        logger.info { "PlanetasController - findAll()" }
        return@withContext planetaRepository.findAll()
    }

    suspend fun planetasGetById(id: Int) : Planeta? = withContext(Dispatchers.IO){
        logger.info { "PlanetasController - findById($id)" }
        return@withContext planetaRepository.findById(id)
    }

    suspend fun planetasSave(planeta: Planeta) : Planeta = withContext(Dispatchers.IO){
        logger.info { "PlanetasController - save($planeta)" }
        return@withContext planetaRepository.save(planeta)
    }

    suspend fun deletePlaneta(planeta: Planeta) = withContext(Dispatchers.IO){
        logger.info { "PlanetasController - delete($planeta)" }
        return@withContext planetaRepository.delete(planeta)
    }
}