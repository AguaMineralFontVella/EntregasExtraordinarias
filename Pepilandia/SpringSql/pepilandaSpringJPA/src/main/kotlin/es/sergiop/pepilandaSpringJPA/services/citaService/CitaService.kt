package es.sergiop.pepilandaSpringJPA.services.citaService

import es.sergiop.pepilandaSpringJPA.models.Cita
import kotlinx.coroutines.flow.Flow

interface CitaService {
    suspend fun findAll(): Flow<Cita>
    suspend fun findById(id: Long): Cita?
    suspend fun save(entity: Cita): Cita
    suspend fun update(entity: Cita): Cita?
    suspend fun delete(entity: Cita): Boolean
}