package es.sergiop.pepilandaSpringJPA.repositories.propietarioRepository

import es.sergiop.pepilandaSpringJPA.models.Propietario
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Interfaz PropietarioRepository que extiende de CoroutineCrudRepository
 */
@Repository
interface PropietarioRepository: CoroutineCrudRepository<Propietario, Long>