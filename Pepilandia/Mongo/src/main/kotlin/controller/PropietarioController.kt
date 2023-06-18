package controller

import com.github.michaelbull.result.onFailure
import exceptions.PropietarioException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.withContext
import models.Propietario
import repository.propietarioRepository.IPropietarioRepository
import repository.propietarioRepository.PropietarioCachedRepository
import validators.validar

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Controlador de los propietarios
 * @param propietarioRepository Repositorio de los propietarios
 * @param propietarioCache Caché de los propietarios
 */
class PropietarioController (
    private val propietarioRepository: IPropietarioRepository,
    private val propietarioCache: PropietarioCachedRepository
    ) {

    /**
     * Función que devuelve todos los propietarios
     * @return Flow de propietarios
     */
    suspend fun findAllPropietario() : Flow<Propietario>? = withContext(Dispatchers.IO){
        val propietarios = propietarioCache.findAll().toList()
        if (propietarios.isEmpty()) {
            propietarioRepository.findAll()
        } else {
            propietarios.asFlow()
        }
    }

    /**
     * Función que devuelve un propietario por su id
     * @param id Id del propietario
     * @return Propietario
     */
    suspend fun findPropietarioById(id: Long): Propietario? = withContext(Dispatchers.IO){
        val propietario = propietarioCache.findById(id)
        if(propietario == null) {
            propietarioRepository.findById(id)
        } else {
            propietario
        }
    }

    /**
     * Función que guarda un propietario
     * @param propietario Propietario a guardar
     */
    suspend fun savePropietario(propietario: Propietario) = withContext(Dispatchers.IO){
        propietario.validar().onFailure { throw PropietarioException.NoValido("Error al validar el propietario, asegúrate de que todos los campos son correctos") }
        propietarioRepository.save(propietario)
        propietarioCache.save(propietario)
    }

    /**
     * Función que borra un propietario
     * @param propietario Propietario a actualizar
     * @return Booleano que indica si se ha borrado o no
     */
    suspend fun deletePropietario(propietario: Propietario): Boolean = withContext(Dispatchers.IO){
        try{
            propietarioRepository.delete(propietario)
            propietarioCache.delete(propietario)
            return@withContext true
        }
        catch (e: Exception){
            return@withContext false
        }
    }

}