package es.sergiop.reintentoextra.repositories.personaRepository

import es.sergiop.reintentoextra.models.Persona
import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository


@Repository
interface PersonaRepository : CoroutineCrudRepository<Persona, ObjectId>