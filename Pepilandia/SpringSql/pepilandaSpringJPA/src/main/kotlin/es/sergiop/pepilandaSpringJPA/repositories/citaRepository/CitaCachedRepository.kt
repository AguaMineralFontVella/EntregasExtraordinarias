package es.sergiop.pepilandaSpringJPA.repositories.citaRepository

import es.sergiop.pepilandaSpringJPA.models.Cita
import kotlinx.coroutines.flow.Flow

interface CitaCachedRepository {
    suspend fun findAll(): Flow<Cita>
    suspend fun findById(id: Long): Cita?
    suspend fun save(entity: Cita): Cita
    suspend fun update(entity: Cita): Cita?
    suspend fun delete(entity: Cita): Boolean
}