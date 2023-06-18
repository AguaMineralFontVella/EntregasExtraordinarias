package es.sergiop.pepilandaSpringJPA.repositories.informeRepository

import es.sergiop.pepilandaSpringJPA.models.Informe
import kotlinx.coroutines.flow.Flow

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Interfaz InformeCachedRepository
 * @property findAll: Flow<Informe>
 * @property findById: suspend (id: Long) -> Informe?
 * @property save: suspend (entity: Informe) -> Informe
 * @property update: suspend (entity: Informe) -> Informe?
 * @property delete: suspend (entity: Informe) -> Boolean
 */
interface InformeCachedRepository {
    suspend fun findAll(): Flow<Informe>
    suspend fun findById(id: Long): Informe?
    suspend fun save(entity: Informe): Informe
    suspend fun update(entity: Informe): Informe?
    suspend fun delete(entity: Informe): Boolean
}