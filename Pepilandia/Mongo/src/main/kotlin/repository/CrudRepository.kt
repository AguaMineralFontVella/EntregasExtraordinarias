package repository

import kotlinx.coroutines.flow.Flow

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Interfaz CrudRepository
 * @property findAll: Flow<T>
 * @property findById: T?
 * @property save: T
 * @property update: T?
 * @property delete: Boolean
 */
interface CrudRepository<T,ID> {
    suspend fun findAll(): Flow<T>
    suspend fun findById(id: ID): T?
    suspend fun save(entity: T): T
    suspend fun update(entity: T): T?
    suspend fun delete(entity: T): Boolean
}