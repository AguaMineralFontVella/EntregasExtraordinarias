package es.sergiop.pepilandaSpringJPA.services.vehiculoService

import es.sergiop.pepilandaSpringJPA.models.Vehiculo
import kotlinx.coroutines.flow.Flow

interface VehiculoService {
    suspend fun findAll(): Flow<Vehiculo>
    suspend fun findById(id: Long): Vehiculo?
    suspend fun save(entity: Vehiculo): Vehiculo
    suspend fun update(entity: Vehiculo): Vehiculo?
    suspend fun delete(entity: Vehiculo): Boolean
}