package es.sergiop.pepilandaSpringJPA.services.informeService

import es.sergiop.pepilandaSpringJPA.models.Informe
import kotlinx.coroutines.flow.Flow

interface InformeService {
    suspend fun findAll(): Flow<Informe>
    suspend fun findById(id: Long): Informe?
    suspend fun save(entity: Informe): Informe
    suspend fun update(entity: Informe): Informe?
    suspend fun delete(entity: Informe): Boolean
}