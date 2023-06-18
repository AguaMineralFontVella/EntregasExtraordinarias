package es.sergiop.pepilandaSpringJPA.services.informeService

import es.sergiop.pepilandaSpringJPA.models.Informe
import kotlinx.coroutines.flow.Flow

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Interfaz InformeService
 * @property findAll
 * @property findById
 * @property save
 * @property update
 * @property delete
 */
interface InformeService {
    suspend fun findAll(): Flow<Informe>
    suspend fun findById(id: Long): Informe?
    suspend fun save(entity: Informe): Informe
    suspend fun update(entity: Informe): Informe?
    suspend fun delete(entity: Informe): Boolean
}