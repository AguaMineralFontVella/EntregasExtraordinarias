package es.sergiop.pepilandaSpringJPA.repositories.vehiculoRepository

import es.sergiop.pepilandaSpringJPA.models.Vehiculo
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Interfaz VehiculoRepository que extiende de CoroutineCrudRepository
 */
@Repository
interface VehiculoRepository: CoroutineCrudRepository<Vehiculo, Long>