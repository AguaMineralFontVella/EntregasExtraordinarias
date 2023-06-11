package es.sergiop.pepilandaSpringJPA.repositories.propietarioRepository

import es.sergiop.pepilandaSpringJPA.models.Propietario
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PropietarioRepository: CoroutineCrudRepository<Propietario, Long>