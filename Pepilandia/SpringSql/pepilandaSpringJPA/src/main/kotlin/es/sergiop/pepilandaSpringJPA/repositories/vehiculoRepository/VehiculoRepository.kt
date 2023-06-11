package es.sergiop.pepilandaSpringJPA.repositories.vehiculoRepository

import es.sergiop.pepilandaSpringJPA.models.Vehiculo
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface VehiculoRepository: CoroutineCrudRepository<Vehiculo, Long>