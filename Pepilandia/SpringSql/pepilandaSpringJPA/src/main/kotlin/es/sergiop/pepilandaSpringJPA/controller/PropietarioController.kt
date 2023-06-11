package es.sergiop.pepilandaSpringJPA.controller

import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import es.sergiop.pepilandaSpringJPA.config.ApiConfig
import es.sergiop.pepilandaSpringJPA.dto.PropietarioCreateDto
import es.sergiop.pepilandaSpringJPA.dto.PropietarioDto
import es.sergiop.pepilandaSpringJPA.exceptions.PropietarioException
import es.sergiop.pepilandaSpringJPA.mappers.toPropietario
import es.sergiop.pepilandaSpringJPA.mappers.toPropietarioDto
import es.sergiop.pepilandaSpringJPA.services.propietarioService.PropietarioServiceImpl
import es.sergiop.pepilandaSpringJPA.validators.validar
import kotlinx.coroutines.flow.toList
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping(ApiConfig.API_PATH + "/propietarios")
class PropietarioController
@Autowired constructor (
    private val propietarioService: PropietarioServiceImpl
) {

    @GetMapping("")
    suspend fun findAll() : ResponseEntity<List<PropietarioDto>> {
        logger.info { "PropietarioController - findAll()" }
        val res = propietarioService.findAll().toList().map { it.toPropietarioDto() }
        return ResponseEntity.ok(res)
    }

    @GetMapping("{id}")
    suspend fun findById(@PathVariable id: Long) : ResponseEntity<PropietarioDto> {
        logger.info { "PropietarioController - findPropietarioById() with id: $id" }
        try{
            val entity = propietarioService.findById(id)?.toPropietarioDto()
            return ResponseEntity.ok(entity)
        } catch (e: PropietarioException.NoEncontrado){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        }
    }

    @PostMapping("")
    suspend fun create(@RequestBody propietarioCreateDto: PropietarioCreateDto) : ResponseEntity<PropietarioDto> {
        logger.info { "PropietarioController - savePropietario() Propietario: $propietarioCreateDto" }
        try{
            val res = propietarioCreateDto.toPropietario().validar().onFailure { return ResponseEntity.badRequest().build() }
                .onSuccess { logger.info { "Propietario validado: $it" } }
            val entity = res.component1()?.let { propietarioService.save(it) }

            return ResponseEntity.status(HttpStatus.CREATED).body(entity?.toPropietarioDto())
        } catch (e: PropietarioException.NoValido){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    @PutMapping("{id}")
    suspend fun update(@PathVariable id: Long, @RequestBody propietarioDto: PropietarioDto) : ResponseEntity<PropietarioDto> {
        logger.info { "PropietarioController - updatePropietario() Propietario: $propietarioDto" }
        try{
            val res = propietarioDto.toPropietario().validar().onFailure { return ResponseEntity.badRequest().build() }
                .onSuccess { logger.info { "Propietario validado: $it" } }
            val entity = res.component1()?.let { propietarioService.update(it)?.toPropietarioDto() ?: throw PropietarioException.NoEncontrado("No se ha encontrado el propietario con id: $id") }

            return ResponseEntity.status(HttpStatus.OK).body(entity)
        } catch (e: PropietarioException.NoEncontrado){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        }
        catch (e: PropietarioException.NoValido){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    @DeleteMapping("{id}")
    suspend fun delete(@PathVariable id: Long) : ResponseEntity<Boolean> {
        logger.info { "PropietarioController - deletePropietario() with id: $id" }
        try{
            val entity = propietarioService.findById(id)?.toPropietarioDto()
            if(entity != null){
                propietarioService.delete(entity.toPropietario())
            }
            return ResponseEntity.noContent().build()
        } catch (e: PropietarioException.NoEncontrado){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        }
    }
}