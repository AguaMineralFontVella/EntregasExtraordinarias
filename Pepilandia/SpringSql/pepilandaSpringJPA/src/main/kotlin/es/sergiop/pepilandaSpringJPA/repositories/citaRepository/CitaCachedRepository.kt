package es.sergiop.pepilandaSpringJPA.repositories.citaRepository

import es.sergiop.pepilandaSpringJPA.models.Cita
import kotlinx.coroutines.flow.Flow

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Interfaz CitaCachedRepository
 * @property findAll: Flow<Cita>
 * @property findById: suspend (id: Long) -> Cita?
 * @property save: suspend (entity: Cita) -> Cita
 * @property update: suspend (entity: Cita) -> Cita?
 * @property delete: suspend (entity: Cita) -> Boolean
 */
interface CitaCachedRepository {
    suspend fun findAll(): Flow<Cita>
    suspend fun findById(id: Long): Cita?
    suspend fun save(entity: Cita): Cita
    suspend fun update(entity: Cita): Cita?
    suspend fun delete(entity: Cita): Boolean
}