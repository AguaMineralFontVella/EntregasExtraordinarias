package es.sergiop.pepilandaSpringJPA.controller

import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import es.sergiop.pepilandaSpringJPA.config.ApiConfig
import es.sergiop.pepilandaSpringJPA.dto.CitaCreateDto
import es.sergiop.pepilandaSpringJPA.dto.CitaDto
import es.sergiop.pepilandaSpringJPA.exceptions.CitaException
import es.sergiop.pepilandaSpringJPA.mappers.toCita
import es.sergiop.pepilandaSpringJPA.mappers.toCitaDto
import es.sergiop.pepilandaSpringJPA.services.citaService.CitaServiceImpl
import es.sergiop.pepilandaSpringJPA.validators.validar
import jakarta.validation.Valid
import kotlinx.coroutines.flow.toList
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

private val logger = KotlinLogging.logger {}

/**
 * Controlador de la entidad Cita
 */
@RestController
@RequestMapping(ApiConfig.API_PATH + "/citas")
class CitaController
    @Autowired constructor (
        private val citaService: CitaServiceImpl
) {
    var countCita = 0
    var countTrabajador1 = 0
    var countTrabajador2 = 0
    var countTrabajador3 = 0
    var countTrabajador4 = 0
    var countTrabajador5 = 0

    /**
     * Función que devuelve todas las citas de la base de datos
     * @return ResponseEntity<List<CitaDto>>
     */
    @GetMapping("")
    suspend fun findAll() : ResponseEntity<List<CitaDto>> {
        logger.info { "CitaController - findAll()" }
        val res = citaService.findAll().toList().map { it.toCitaDto() }
        return ResponseEntity.ok(res)
    }

    /**
     * Función que devuelve una cita de la base de datos a partir de su id
     * @param id: Long
     * @return ResponseEntity<CitaDto>
     */
    @GetMapping("{id}")
    suspend fun findById(@PathVariable id: Long) : ResponseEntity<CitaDto> {
        logger.info { "CitaController - findCitaById() with id: $id" }
        try{
            val entity = citaService.findById(id)?.toCitaDto()
            return ResponseEntity.ok(entity)
        }
        catch (e: CitaException.NoEncontrado){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        }
    }

    /**
     * Función que crea una cita en la base de datos y comprueba que no se superen los 8 registros por intervalo (4 por trabajador)
     * @param citaCreateDto: CitaCreateDto
     * @return ResponseEntity<CitaDto>
     */
    @PostMapping("")
    suspend fun create(@Valid @RequestBody citaCreateDto: CitaCreateDto) : ResponseEntity<CitaDto> {
        logger.info { "CitaController - saveCita() Cita: $citaCreateDto" }
        try{
            val res = citaCreateDto.toCita().validar().onFailure { return ResponseEntity.badRequest().build() }
                .onSuccess { logger.info { "Cita validada: $it" } }

            res.component1()?.idTrabajador?.let {
                when (it) {
                    1 -> {
                        countTrabajador1++
                        if(countTrabajador1 <= 4) {
                            countCita++
                            if (countCita > 8) {
                                throw CitaException.NoGuardado("El número máximo de citas para el intervalo ya ha sido alcanzado")
                            }
                            else{
                                logger.info { "El trabajador 1 se encuentra realizando $countTrabajador1 citas" }
                            }
                        }
                        else {
                            if (countCita >= 8) {
                                throw CitaException.NoGuardado("El número máximo de citas para el intervalo ya ha sido alcanzado")
                            }
                            else throw CitaException.NoValido("El trabajador 1 ya tiene el número máximo citas asignadas para el intervalo")
                        }
                    }
                    2 -> {
                        countTrabajador2++
                        if(countTrabajador2 <= 4) {
                            countCita++
                            if (countCita > 8) {
                                throw CitaException.NoGuardado("El número máximo de citas para el intervalo ya ha sido alcanzado")
                            }
                            else{
                                logger.info { "El trabajador 2 se encuentra realizando $countTrabajador2 citas" }
                            }
                        }
                        else {
                            if (countCita >= 8) {
                                throw CitaException.NoGuardado("El número máximo de citas para el intervalo ya ha sido alcanzado")
                            }
                            else throw CitaException.NoValido("El trabajador 2 ya tiene el número máximo citas asignadas para el intervalo")
                        }
                    }
                    3 -> {
                        countTrabajador3++
                        if(countTrabajador3 <= 4) {
                            countCita++
                            if (countCita > 8) {
                                throw CitaException.NoGuardado("El número máximo de citas para el intervalo ya ha sido alcanzado")
                            }
                            else{
                                logger.info { "El trabajador 3 se encuentra realizando $countTrabajador3 citas" }
                            }
                        }
                        else {
                            if (countCita >= 8) {
                                throw CitaException.NoGuardado("El número máximo de citas para el intervalo ya ha sido alcanzado")
                            }
                            else throw CitaException.NoValido("El trabajador 3 ya tiene el número máximo citas asignadas para el intervalo")
                        }
                    }
                    4 -> {
                        countTrabajador4++
                        if(countTrabajador4 <= 4) {
                            countCita++
                            if (countCita > 8) {
                                throw CitaException.NoGuardado("El número máximo de citas para el intervalo ya ha sido alcanzado")
                            }
                            else{
                                logger.info { "El trabajador 4 se encuentra realizando $countTrabajador4 citas" }
                            }
                        }
                        else {
                            if (countCita >= 8) {
                                throw CitaException.NoGuardado("El número máximo de citas para el intervalo ya ha sido alcanzado")
                            }
                            else throw CitaException.NoValido("El trabajador 4 ya tiene el número máximo citas asignadas para el intervalo")
                        }
                    }
                    5 -> {
                        countTrabajador5++
                        if(countTrabajador5 <= 4) {
                            countCita++
                            if (countCita > 8) {
                                throw CitaException.NoGuardado("El número máximo de citas para el intervalo ya ha sido alcanzado")
                            }
                            else{
                                logger.info { "El trabajador 5 se encuentra realizando $countTrabajador5 citas" }
                            }
                        }
                        else {
                            if (countCita >= 8) {
                                throw CitaException.NoGuardado("El número máximo de citas para el intervalo ya ha sido alcanzado")
                            }
                            else throw CitaException.NoValido("El trabajador 5 ya tiene el número máximo citas asignadas para el intervalo")
                        }
                    }
                    else -> {
                        logger.info { "Trabajador no encontrado" }
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
                    }
                }
            }

            val entity = res.component1()?.let { citaService.save(it) }

            return ResponseEntity.status(HttpStatus.CREATED).body(entity?.toCitaDto())
        }
        catch (e: CitaException.NoValido){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    /**
     * Función que actualiza una cita en la base de datos
     * @param id: Long
     * @param citaDto: CitaDto
     * @return ResponseEntity<CitaDto>
     */
    @PutMapping("{id}")
    suspend fun update(@PathVariable id: Long, @Valid @RequestBody citaDto: CitaDto) : ResponseEntity<CitaDto> {
        logger.info { "CitaController - updateCita() Cita: $citaDto" }
        try{
            val res = citaDto.toCita().validar().onFailure { return ResponseEntity.badRequest().build() }
                .onSuccess { logger.info { "Cita validada: $it" } }
            val entity = res.component1()?.let { citaService.update(it)?.toCitaDto() ?: throw CitaException.NoEncontrado("No se ha encontrado la cita con id: $id") }
            return ResponseEntity.status(HttpStatus.OK).body(entity)
        }
        catch (e: CitaException.NoEncontrado){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        }
        catch (e: CitaException.NoValido){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    /**
     * Función que elimina una cita de la base de datos
     * @param id: Long
     * @return ResponseEntity<Boolean>
     */
    @DeleteMapping("{id}")
    suspend fun delete(@PathVariable id: Long) : ResponseEntity<Boolean> {
        logger.info { "CitaController - deleteCita() with id: $id" }
        try{
            val entity = citaService.findById(id)?.toCitaDto()
            if (entity != null) {
                citaService.delete(entity.toCita())
            }
            return ResponseEntity.noContent().build()
        }
        catch (e: CitaException.NoEncontrado){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        }
    }

}