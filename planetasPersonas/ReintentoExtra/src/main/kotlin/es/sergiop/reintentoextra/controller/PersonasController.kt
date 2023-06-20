package es.sergiop.reintentoextra.controller

import es.sergiop.reintentoextra.models.Persona
import es.sergiop.reintentoextra.models.Planeta
import es.sergiop.reintentoextra.repositories.personaRepository.PersonaRepository
import es.sergiop.reintentoextra.repositories.planetaRepository.PlanetaRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.withContext
import mu.KotlinLogging
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

private val logger = KotlinLogging.logger {}

@Controller
class PersonasController
@Autowired constructor(
    private val personaRepository: PersonaRepository //Mongo con Spring
){

    val planetasController = PlanetasController(PlanetaRepositoryImpl())

    //PERSONAS
    suspend fun personasGetAll(): Flow<Persona> = withContext(Dispatchers.IO){
        logger.info { "PersonasController - findAll()" }
        return@withContext personaRepository.findAll()
    }

    suspend fun personasGetById(id: ObjectId): Persona? = withContext(Dispatchers.IO){
        logger.info { "PersonasController - findById($id)" }
        return@withContext personaRepository.findById(id)
    }

    suspend fun personasSave(persona: Persona): Persona = withContext(Dispatchers.IO){
        logger.info { "PersonasController - save($persona)" }
        return@withContext personaRepository.save(persona)
    }

    suspend fun deletePersona(id: ObjectId) = withContext(Dispatchers.IO){
        logger.info { "PersonasController - delete($id)" }
        return@withContext personaRepository.deleteById(id)
    }

    suspend fun deleteAllPersonas() = withContext(Dispatchers.IO){
        logger.info { "PersonasController - deleteAll()" }
        return@withContext personaRepository.deleteAll()
    }

    suspend fun personasGetByPlaneta(planeta1: Planeta): Flow<Persona> = withContext(Dispatchers.IO) {
        logger.info { "PersonasController - getByPlaneta($planeta1)" }
        val personas = personaRepository.findAll()
        return@withContext personas.filter { it.planeta.lowercase() == planeta1.nombre.lowercase() }
    }

    suspend fun getInformacionPlaneta(persona: Persona): Planeta? = withContext(Dispatchers.IO){
        logger.info { "PersonasController - getInformacionPlaneta($persona)" }
        val listaPlanetas = planetasController.planetasGetAll().toList()
        var planetaEncontrado : Planeta? = null
        listaPlanetas.forEach {
            if (it.nombre.lowercase() == persona.planeta.lowercase()){
                planetaEncontrado = it
            }
        }
        return@withContext planetaEncontrado
    }
}