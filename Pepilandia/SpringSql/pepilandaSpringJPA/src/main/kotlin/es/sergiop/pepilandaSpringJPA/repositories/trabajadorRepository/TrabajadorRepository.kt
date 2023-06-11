package es.sergiop.pepilandaSpringJPA.repositories.trabajadorRepository

import es.sergiop.pepilandaSpringJPA.models.Trabajador
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TrabajadorRepository : CoroutineCrudRepository<Trabajador, Long>