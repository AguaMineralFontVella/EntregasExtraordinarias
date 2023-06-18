package es.sergiop.pepilandaSpringJPA.repositories.informeRepository

import es.sergiop.pepilandaSpringJPA.models.Informe
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Interfaz InformeRepository que extiende de CoroutineCrudRepository
 */
@Repository
interface InformeRepository : CoroutineCrudRepository<Informe, Long>