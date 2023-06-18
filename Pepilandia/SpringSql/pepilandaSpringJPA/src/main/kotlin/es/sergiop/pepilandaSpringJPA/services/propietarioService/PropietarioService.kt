package es.sergiop.pepilandaSpringJPA.services.propietarioService

import es.sergiop.pepilandaSpringJPA.models.Propietario
import kotlinx.coroutines.flow.Flow

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Interfaz PropietarioService
 * @property findAll
 * @property findById
 * @property save
 * @property update
 * @property delete
 */
interface PropietarioService {
    suspend fun findAll(): Flow<Propietario>
    suspend fun findById(id: Long): Propietario?
    suspend fun save(entity: Propietario): Propietario
    suspend fun update(entity: Propietario): Propietario?
    suspend fun delete(entity: Propietario): Boolean
}