package controller

import com.github.michaelbull.result.onFailure
import exceptions.CitaException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import mappers.toCitaDto
import models.Cita
import models.ResponseSuccess
import models.Trabajador
import repository.citaRepository.CitaCachedRepository
import repository.citaRepository.ICitaRepository
import validators.validar

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Controlador de las citas
 * @param citaRepository Repositorio de las citas
 * @param citaCache Caché de las citas
 */
class CitaController(
    private val citaRepository: ICitaRepository,
    private val citaCache: CitaCachedRepository
) {

    private var listaTrabajadores : MutableList<Trabajador>? = null

    private val _citasFlow = MutableSharedFlow<String>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val citasFlow = _citasFlow.asSharedFlow()

    private var countCita = 0
    private var countTrabajador1 = 0
    private var countTrabajador2 = 0
    private var countTrabajador3 = 0
    private var countTrabajador4 = 0
    private var countTrabajador5 = 0

    /**
     * Función que devuelve todas las citas
     * @return Flow de citas
     */
    suspend fun findAllCita() : Flow<Cita>? = withContext(Dispatchers.IO){
        val citas = citaCache.findAll().toList()
        if(citas.isEmpty()){
            citaRepository.findAll()
        }else{
            citas.asFlow()
        }
    }

    /**
     * Función que devuelve una cita por su id
     * @param id Id de la cita
     * @return Cita
     */
    suspend fun findCitaById(id: Long): Cita = withContext(Dispatchers.IO){
        val cita = citaCache.findById(id)
        if(cita == null){
            return@withContext citaRepository.findById(id)!!
        }else{
            cita
        }
    }

    /**
     * Función que guarda una cita y se asegura de que no se supera el número máximo de citas por intervalo
     * @param cita Cita a guardar
     * @return Cita guardada
     */
    suspend fun saveCita(cita: Cita): Cita = withContext(Dispatchers.IO){
        cita.validar().onFailure { CitaException.NoValido("Error al validar la cita, asegúrate de que todos los campos son correctos") }

        val responseError400 = CitaException.NoGuardado("El número máximo de citas para el intervalo ya ha sido alcanzado")
        val responseError400Max = CitaException.NoGuardado("No se pudo crear la cita")

        println(listaTrabajadores)

        cita.idTrabajador.let{
            when(it){
                listaTrabajadores?.get(0)?.id.toString() -> {
                    countTrabajador1++
                    if(countTrabajador1 <= 4) {
                        countCita++
                        if (countCita > 8) {
                            throw responseError400
                        }
                        else{
                            citaRepository.save(cita)
                            val cita = citaCache.save(cita)
                            _citasFlow.emit("Se ha añadido la cita $cita")
                            if(cita == null){
                                throw responseError400Max
                            }
                            else {
                                ResponseSuccess(201, cita.toCitaDto())
                            }
                        }
                    }
                    else {
                        if (countCita >= 8) {
                            throw responseError400Max
                        }
                        throw CitaException.NoGuardado("El trabajador 1 ya tiene el número máximo citas asignadas para el intervalo")
                    }
                }
                listaTrabajadores?.get(1)?.id.toString() -> {
                    countTrabajador2++
                    if(countTrabajador2 <= 4) {
                        countCita++
                        if (countCita > 8) {
                            throw responseError400Max
                        }
                        else{
                            citaRepository.save(cita)
                            val cita = citaCache.save(cita)
                            _citasFlow.emit("Se ha añadido la cita $cita")
                            if(cita == null){
                                throw responseError400
                            }
                            else {
                                ResponseSuccess(201, cita.toCitaDto())
                            }
                        }
                    }
                    else {
                        if (countCita >= 8) {
                            throw responseError400Max
                        }
                        throw CitaException.NoGuardado("El trabajador 2 ya tiene el número máximo citas asignadas para el intervalo")
                    }
                }
                listaTrabajadores?.get(2)?.id.toString() -> {
                    countTrabajador3++
                    if(countTrabajador3 <= 4) {
                        countCita++
                        if (countCita > 8) {
                            throw responseError400Max
                        }
                        else{
                            citaRepository.save(cita)
                            val cita = citaCache.save(cita)
                            _citasFlow.emit("Se ha añadido la cita $cita")
                            if(cita == null){
                                throw responseError400
                            }
                            else {
                                ResponseSuccess(201, cita.toCitaDto())
                            }
                        }
                    }
                    else {
                        if (countCita >= 8) {
                            throw responseError400Max
                        }
                        throw CitaException.NoGuardado("El trabajador 3 ya tiene el número máximo citas asignadas para el intervalo")
                    }
                }
                listaTrabajadores?.get(3)?.id.toString() -> {
                    countTrabajador4++
                    if(countTrabajador4 <= 4) {
                        countCita++
                        if (countCita > 8) {
                            throw responseError400Max
                        }
                        else{
                            citaRepository.save(cita)
                            val cita = citaCache.save(cita)
                            _citasFlow.emit("Se ha añadido la cita $cita")
                            if(cita == null){
                                throw responseError400
                            }
                            else {
                                ResponseSuccess(201, cita.toCitaDto())
                            }
                        }
                    }
                    else {
                        if (countCita >= 8) {
                            throw responseError400Max
                        }
                        throw CitaException.NoGuardado("El trabajador 4 ya tiene el número máximo citas asignadas para el intervalo")
                    }
                }
                listaTrabajadores?.get(4)?.id.toString() -> {
                    countTrabajador5++
                    if(countTrabajador5 <= 4) {
                        countCita++
                        if (countCita > 8) {
                            throw responseError400Max
                        }
                        else{
                            citaRepository.save(cita)
                            val cita = citaCache.save(cita)
                            _citasFlow.emit("Se ha añadido la cita $cita")
                            if(cita == null){
                                throw responseError400
                            }
                            else {
                                ResponseSuccess(201, cita.toCitaDto())
                            }
                        }
                    }
                    else {
                        if (countCita >= 8) {
                            throw responseError400Max
                        }
                        throw CitaException.NoGuardado("El trabajador 5 ya tiene el número máximo citas asignadas para el intervalo")
                    }
                }
                else -> {
                    throw CitaException.NoValido("No existe el trabajador con id: $it")
                }
            }
        }
        return@withContext cita
    }

    /**
     * Función que borra una cita
     * @param cita Cita a borrar
     * @return Booleano que indica si se ha borrado o no
     */
    suspend fun deleteCita(cita: Cita): Boolean = withContext(Dispatchers.IO){
        try{
            citaCache.delete(cita)
            _citasFlow.emit("Se ha borrado la cita $cita")
            return@withContext true
        }
        catch (e: Exception){
            return@withContext false
        }
    }

    /**
     * Función que añade a la lista de trabajadores los trabajadores de la base de datos
     */
    fun addListaTrabajadores(lista: MutableList<Trabajador>){
        listaTrabajadores = lista
    }
}