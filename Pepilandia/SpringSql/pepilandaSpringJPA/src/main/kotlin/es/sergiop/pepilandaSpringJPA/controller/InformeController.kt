package es.sergiop.pepilandaSpringJPA.controller

import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import es.sergiop.pepilandaSpringJPA.config.ApiConfig
import es.sergiop.pepilandaSpringJPA.dto.InformeCreateDto
import es.sergiop.pepilandaSpringJPA.dto.InformeDto
import es.sergiop.pepilandaSpringJPA.exceptions.InformeException
import es.sergiop.pepilandaSpringJPA.mappers.toInforme
import es.sergiop.pepilandaSpringJPA.mappers.toInformeDto
import es.sergiop.pepilandaSpringJPA.services.informeService.InformeServiceImpl
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
 * Controlador de la entidad Informe
 */

@RestController
@RequestMapping(ApiConfig.API_PATH + "/informes")
class InformeController
    @Autowired constructor (
        private val informeService: InformeServiceImpl
) {

    /**
     * Función que devuelve todos los informes
     * @return ResponseEntity<List<InformeDto>>
     */
    @GetMapping("")
    suspend fun findAll() : ResponseEntity<List<InformeDto>> {
        logger.info { "InformeController - findAll()" }
        val res = informeService.findAll().toList().map { it.toInformeDto() }
        return ResponseEntity.ok(res)
    }

    /**
     * Función que devuelve un informe por su id
     * @param id: Long
     * @return ResponseEntity<InformeDto>
     */
    @GetMapping("{id}")
    suspend fun findById(@PathVariable id: Long): ResponseEntity<InformeDto> {
        logger.info { "InformeController - findInformeById() with id: $id" }
        try{
            val entity = informeService.findById(id)?.toInformeDto()
            return ResponseEntity.ok(entity)
        }
        catch (e: InformeException.NoEncontrado){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        }
    }

    /**
     * Función que crea un informe
     * @param informeCreateDto: InformeCreateDto
     * @return ResponseEntity<InformeDto>
     */
    @PostMapping("")
    suspend fun create(@Valid @RequestBody informeCreateDto: InformeCreateDto) : ResponseEntity<InformeDto> {
        logger.info { "InformeController - saveInforme() Informe: $informeCreateDto" }
        try{
            val res = informeCreateDto.toInforme().validar().onFailure { return ResponseEntity.badRequest().build() }
                .onSuccess { logger.info { "Informe validado: $it" } }
            val entity = res.component1()?.let { informeService.save(it) }
            return ResponseEntity.status(HttpStatus.CREATED).body(entity?.toInformeDto())

        }
        catch (e: InformeException.NoValido){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        }
    }

    /**
     * Función que actualiza un informe
     * @param id: Long
     * @param informeCreateDto: InformeCreateDto
     * @return ResponseEntity<InformeDto>
     */
    @PutMapping("{id}")
    suspend fun update(@PathVariable id: Long, @Valid @RequestBody informeCreateDto: InformeCreateDto) : ResponseEntity<InformeDto> {
        logger.info { "InformeController - updateInforme() with id: $id" }
        try{
            val res = informeCreateDto.toInforme().validar().onFailure { return ResponseEntity.badRequest().build() }
                .onSuccess { logger.info { "Informe validado: $it" } }
            val entity = res.component1()?.let { informeService.update(it)?.toInformeDto() ?: throw InformeException.NoEncontrado("No se ha encontrado el informe con id: $id") }
            return ResponseEntity.ok(entity)
        }
        catch (e: InformeException.NoEncontrado){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        }
        catch (e: InformeException.NoValido){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        }
    }

    /**
     * Función que elimina un informe
     * @param id: Long
     * @return ResponseEntity<Unit>
     */
    @DeleteMapping("{id}")
    suspend fun delete(@PathVariable id: Long) : ResponseEntity<Unit> {
        logger.info { "InformeController - deleteInforme() with id: $id" }
        try{
            val entity = informeService.findById(id)?.toInformeDto()
            if (entity != null) {
                informeService.delete(entity.toInforme())
            }
            return ResponseEntity.noContent().build()
        }
        catch (e: InformeException.NoEncontrado){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        }
    }
}