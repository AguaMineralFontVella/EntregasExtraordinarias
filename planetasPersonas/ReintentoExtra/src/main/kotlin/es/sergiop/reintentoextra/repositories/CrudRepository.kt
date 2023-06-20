package es.sergiop.reintentoextra.repositories

import kotlinx.coroutines.flow.Flow

interface CrudRepository<T, ID> {
    fun findAll(): Flow<T>
    fun findById(id: ID): T?
    fun save(entity: T): T
    fun delete(entity: T): Boolean
}