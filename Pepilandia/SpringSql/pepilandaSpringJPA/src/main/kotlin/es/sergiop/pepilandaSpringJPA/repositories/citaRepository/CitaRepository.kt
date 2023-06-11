package es.sergiop.pepilandaSpringJPA.repositories.citaRepository

import org.springframework.stereotype.Repository
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import es.sergiop.pepilandaSpringJPA.models.Cita

@Repository
interface CitaRepository : CoroutineCrudRepository<Cita, Long>