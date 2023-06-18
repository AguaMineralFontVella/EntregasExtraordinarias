package es.sergiop.pepilandaSpringJPA.repositories.citaRepository

import org.springframework.stereotype.Repository
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import es.sergiop.pepilandaSpringJPA.models.Cita

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Interfaz CitaRepository que extiende de CoroutineCrudRepository
 */
@Repository
interface CitaRepository : CoroutineCrudRepository<Cita, Long>