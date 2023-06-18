package es.sergiop.pepilandaSpringJPA.repositories.trabajadorRepository

import es.sergiop.pepilandaSpringJPA.models.Trabajador
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Interfaz TrabajadorRepository que extiende de CoroutineCrudRepository
 */
@Repository
interface TrabajadorRepository : CoroutineCrudRepository<Trabajador, Long>