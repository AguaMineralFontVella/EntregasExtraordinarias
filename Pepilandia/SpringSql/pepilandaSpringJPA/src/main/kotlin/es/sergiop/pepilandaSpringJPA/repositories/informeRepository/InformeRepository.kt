package es.sergiop.pepilandaSpringJPA.repositories.informeRepository

import es.sergiop.pepilandaSpringJPA.models.Informe
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface InformeRepository : CoroutineCrudRepository<Informe, Long>