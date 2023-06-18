package controller

import com.github.michaelbull.result.onFailure
import dto.InformeCreateDto
import exceptions.InformeException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.withContext
import mappers.toInforme
import models.Informe
import repository.informeRepository.IInformeRepository
import repository.informeRepository.InformeCachedRepository
import validators.validar

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Controlador de los informes
 * @param informeRepository Repositorio de los informes
 * @param informeCache Caché de los informes
 */
class InformeController (
    private val informeRepository: IInformeRepository,
    private val informeCache: InformeCachedRepository
    ) {

    /**
     * Función que crea un informe con datos aleatorios
     * @param idCita Id de la cita
     * @param idTrabajador Id del trabajador
     * @param idVehiculo Id del vehículo
     * @param dniPropietario Dni del propietario
     * @return Informe
     */
    fun hacerInforme(idCita: String, idTrabajador: String, idVehiculo: String, dniPropietario: String): Informe {
        return InformeCreateDto(
            frenado = (1..10).random().toString(),
            frenadoIsApto = ((0..1).random() == 1).toString(),
            contaminacion = (20..50).random().toString(),
            lucesIsApto = ((0..1).random() == 1).toString(),
            idCita = idCita,
            idTrabajador = idTrabajador,
            idVehiculo = idVehiculo,
            dniPropietario = dniPropietario,
            isFavorable = ((0..1).random()).toString()
        ).toInforme()
    }

    /**
     * Función que encuentra los informes
     * @return Flow<Informe>
     */
    suspend fun findAllInforme() : Flow<Informe>? = withContext(Dispatchers.IO){
        val informes = informeCache.findAll().toList()
        if (informes.isEmpty()) {
            informeRepository.findAll()
        } else {
            informes.asFlow()
        }
    }

    /**
     * Función que encuentra los informes buscando por Id
     * @param id Long
     * @return Informe
     */
    suspend fun findInformeById(id: Long): Informe? = withContext(Dispatchers.IO){
        val informe = informeCache.findById(id)
        if (informe == null) {
            informeRepository.findById(id)
        } else {
            informe
        }
    }

    /**
     * Función que guarda un informe
     * @param informe Informe
     */
    suspend fun saveInforme(informe: Informe) = withContext(Dispatchers.IO){
        informe.validar().onFailure { throw InformeException.NoValido("Error al validar el informe, asegúrate de que todos los campos son correctos") }
        informeRepository.save(informe)
        informeCache.save(informe)
    }

    /**
     * Función que borra un informe
     * @param informe Informe a borrar
     * @return Booleano que indica si se ha borrado o no
     */
    suspend fun deleteInforme(informe: Informe): Boolean = withContext(Dispatchers.IO){
        try{
            informeRepository.delete(informe)
            informeCache.delete(informe)
            return@withContext true
        }
        catch (e: Exception){
            return@withContext false
        }
    }
}