package es.sergiop.pepilandaSpringJPA.repositories

import kotlinx.coroutines.flow.Flow

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Interfaz CrudRepository
 * @property findAll
 * @property findById
 * @property save
 * @property update
 * @property delete
 */
interface CrudRepository<T, ID> {
    suspend fun findAll(): Flow<T>
    suspend fun findById(id: ID): T?
    suspend fun save(entity: T): T
    suspend fun update(entity: T): T?
    suspend fun delete(entity: T): Boolean
}