package es.sergiop.pepilandaSpringJPA.services.trabajadorService

import es.sergiop.pepilandaSpringJPA.models.Trabajador
import kotlinx.coroutines.flow.Flow

interface TrabajadorService {
    suspend fun findAll(): Flow<Trabajador>
    suspend fun findById(id: Long): Trabajador?
    suspend fun save(entity: Trabajador): Trabajador
    suspend fun update(entity: Trabajador): Trabajador?
    suspend fun delete(entity: Trabajador): Boolean
}