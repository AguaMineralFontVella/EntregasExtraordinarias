package es.sergiop.pepilandaSpringJPA.controller

import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import es.sergiop.pepilandaSpringJPA.config.ApiConfig
import es.sergiop.pepilandaSpringJPA.dto.VehiculoCreateDto
import es.sergiop.pepilandaSpringJPA.dto.VehiculoDto
import es.sergiop.pepilandaSpringJPA.exceptions.VehiculoException
import es.sergiop.pepilandaSpringJPA.mappers.toVehiculo
import es.sergiop.pepilandaSpringJPA.mappers.toVehiculoDto
import es.sergiop.pepilandaSpringJPA.services.vehiculoService.VehiculoServiceImpl
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
 * Controlador de la entidad Vehiculo
 */
@RestController
@RequestMapping(ApiConfig.API_PATH + "/vehiculos")
class VehiculoController
@Autowired constructor (
    private val vehiculoService: VehiculoServiceImpl
) {

    /**
     * Función que devuelve todos los vehiculos
     * @return ResponseEntity<List<VehiculoDto>>
     */
    @GetMapping("")
    suspend fun findAll() : ResponseEntity<List<VehiculoDto>> {
        logger.info { "VehiculoController - findAll()" }
        val res = vehiculoService.findAll().toList().map { it.toVehiculoDto() }
        return ResponseEntity.ok(res)
    }

    /**
     * Función que devuelve un vehiculo por su id
     * @param id: Long
     * @return ResponseEntity<VehiculoDto>
     */
    @GetMapping("{id}")
    suspend fun findById(@PathVariable id: Long) : ResponseEntity<VehiculoDto> {
        logger.info { "VehiculoController - findVehiculoById() with id: $id" }
        try{
            val entity = vehiculoService.findById(id)?.toVehiculoDto()
            return ResponseEntity.ok(entity)
        }
        catch (e: VehiculoException.NoEncontrado){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        }
    }

    /**
     * Función que crea un vehiculo
     * @param vehiculoCreateDto: VehiculoCreateDto
     * @return ResponseEntity<VehiculoDto>
     */
    @PostMapping("")
    suspend fun create(@Valid @RequestBody vehiculoCreateDto: VehiculoCreateDto) : ResponseEntity<VehiculoDto> {
        logger.info { "VehiculoController - saveVehiculo() Vehiculo: $vehiculoCreateDto" }
        try{
            val res = vehiculoCreateDto.toVehiculo().validar().onFailure { return ResponseEntity.badRequest().build() }
                .onSuccess { logger.info { "Vehiculo validado: $it" } }
            val entity = res.component1()?.let { vehiculoService.save(it) }

            return ResponseEntity.status(HttpStatus.CREATED).body(entity?.toVehiculoDto())
        }
        catch (e: VehiculoException.NoValido){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    /**
     * Función que actualiza un vehiculo
     * @param id: Long
     * @param vehiculoDto: VehiculoDto
     * @return ResponseEntity<VehiculoDto>
     */
    @PutMapping("{id}")
    suspend fun update(@PathVariable id: Long, @Valid @RequestBody vehiculoDto: VehiculoDto) : ResponseEntity<VehiculoDto> {
        logger.info { "VehiculoController - updateVehiculo() Vehiculo: $vehiculoDto" }
        try{
            val res = vehiculoDto.toVehiculo().validar().onFailure { return ResponseEntity.badRequest().build() }
                .onSuccess { logger.info { "Vehiculo validado: $it" } }
            val entity = res.component1()?.let { vehiculoService.update(it)?.toVehiculoDto() ?: throw VehiculoException.NoEncontrado("No se ha encontrado el vehiculo con id: $id") }

            return ResponseEntity.status(HttpStatus.CREATED).body(entity)
        }
        catch (e: VehiculoException.NoValido){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
        catch (e: VehiculoException.NoEncontrado){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        }
    }

    /**
     * Función que elimina un vehiculo
     * @param id: Long
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("{id}")
    suspend fun delete(@PathVariable id: Long) : ResponseEntity<Void> {
        logger.info { "VehiculoController - deleteVehiculo() with id: $id" }
        try{
            val entity = vehiculoService.findById(id)?.toVehiculoDto()
            if (entity != null) {
                vehiculoService.delete(entity.toVehiculo())
            }
            return ResponseEntity.noContent().build()
        }
        catch (e: VehiculoException.NoEncontrado){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        }
    }
}