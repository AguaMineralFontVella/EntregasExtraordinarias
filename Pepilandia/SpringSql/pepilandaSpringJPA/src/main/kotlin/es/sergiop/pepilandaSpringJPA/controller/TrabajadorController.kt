package es.sergiop.pepilandaSpringJPA.controller

import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import es.sergiop.pepilandaSpringJPA.config.ApiConfig
import es.sergiop.pepilandaSpringJPA.dto.TrabajadorCreateDto
import es.sergiop.pepilandaSpringJPA.dto.TrabajadorDto
import es.sergiop.pepilandaSpringJPA.exceptions.TrabajadorException
import es.sergiop.pepilandaSpringJPA.mappers.toTrabajador
import es.sergiop.pepilandaSpringJPA.mappers.toTrabajadorDto
import es.sergiop.pepilandaSpringJPA.models.Consulta
import es.sergiop.pepilandaSpringJPA.models.Trabajador
import es.sergiop.pepilandaSpringJPA.services.trabajadorService.TrabajadorServiceImpl
import es.sergiop.pepilandaSpringJPA.validators.validar
import kotlinx.coroutines.flow.toList
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping(ApiConfig.API_PATH + "/trabajadores")
class TrabajadorController
@Autowired constructor (
    private val trabajadorService: TrabajadorServiceImpl
) {

    @GetMapping("")
    suspend fun findAll() : ResponseEntity<List<TrabajadorDto>> {
        logger.info { "TrabajadorController - findAll()" }
        val res = trabajadorService.findAll().toList().map {
            val salario = calcularSalario(it)
            it.salario = salario
            it.toTrabajadorDto()
        }
        return ResponseEntity.ok(res)
    }

    @GetMapping("{id}")
    suspend fun findById(@PathVariable id: Long) : ResponseEntity<TrabajadorDto> {
        logger.info { "TrabajadorController - findTrabajadorById() with id: $id" }
        try{
            val entity = trabajadorService.findById(id)
            val salario = entity?.let { calcularSalario(it) }
            if (salario != null) {
                entity.salario = salario
            }
            return ResponseEntity.ok(entity?.toTrabajadorDto())
        } catch (e: TrabajadorException.NoEncontrado){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        }
    }

    @GetMapping("consultas")
    suspend fun doConsultas() : ResponseEntity<String> {
        logger.info { "TrabajadorController - doConsultas()" }
        val trabajadoresList = trabajadorService.findAll().toList().map {
            val salario = calcularSalario(it)
            it.salario = salario
            it
        }
        val trabajadorMasGana = trabajadoresList.filter { !it.isResponsable }.maxByOrNull { it.salario }
        val salarioMedio = trabajadoresList.filter { !it.isResponsable }.map { it.salario }.average()
        val salarioMedioEspecialidad = trabajadoresList.groupBy { it.especialidad }.mapValues { it -> it.value.map { it.salario }.average() }
        val trabajadorMenosAntiguedad = trabajadoresList.minByOrNull { it.fechaContratacion }
        val trabajadoresOrdenados = trabajadoresList.sortedBy { it.especialidad }
        val json = Json { prettyPrint = true }
        val consulta = json.encodeToString(Consulta(
            trabajadorMasGana.toString(),
            salarioMedio,
            salarioMedioEspecialidad,
            trabajadorMenosAntiguedad.toString(),
            trabajadoresOrdenados.toString()
        ))
        return ResponseEntity.ok(consulta)
    }

    @PostMapping("")
    suspend fun create(@RequestBody trabajadorCreateDto: TrabajadorCreateDto) : ResponseEntity<TrabajadorDto> {
        logger.info { "TrabajadorController - saveTrabajador() Trabajador: $trabajadorCreateDto" }
        try{
            val trabajadores = trabajadorService.findAll().toList()
            val res = trabajadorCreateDto.toTrabajador().validar(trabajadores).onFailure { return ResponseEntity.badRequest().build() }
                .onSuccess { logger.info { "Trabajador validado: $it" } }
            val salario = res.component1()?.let { calcularSalario(it) }
            if (salario != null) {
                res.component1()?.salario = salario
            }
            val entity = res.component1()?.let { trabajadorService.save(it) }

            return ResponseEntity.status(HttpStatus.CREATED).body(entity?.toTrabajadorDto())
        } catch (e: TrabajadorException.NoValido){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    @PutMapping("{id}")
    suspend fun update(@PathVariable id: Long, @RequestBody trabajadorDto: TrabajadorDto) : ResponseEntity<TrabajadorDto> {
        logger.info { "TrabajadorController - updateTrabajador() Trabajador: $trabajadorDto" }
        try{
            val trabajadores = trabajadorService.findAll().toList()
            val res = trabajadorDto.toTrabajador().validar(trabajadores).onFailure { return ResponseEntity.badRequest().build() }
                .onSuccess { logger.info { "Trabajador validado: $it" } }
            val salario = res.component1()?.let { calcularSalario(it) }
            if (salario != null) {
                res.component1()?.salario = salario
            }
            val entity = res.component1()?.let { trabajadorService.update(it)?.toTrabajadorDto() } ?: throw TrabajadorException.NoEncontrado("No se ha encontrado el trabajador con id: $id")
            return ResponseEntity.status(HttpStatus.OK).body(entity)
        } catch (e: TrabajadorException.NoValido){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        } catch (e: TrabajadorException.NoEncontrado){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        }
    }

    @DeleteMapping("{id}")
    suspend fun delete(@PathVariable id: Long) : ResponseEntity<Boolean> {
        logger.info { "TrabajadorController - deleteTrabajador() with id: $id" }
        try{
            val entity = trabajadorService.findById(id)?.toTrabajadorDto()
            if(entity != null) trabajadorService.delete(entity.toTrabajador())
            return ResponseEntity.status(HttpStatus.OK).body(true)
        } catch (e: TrabajadorException.NoEncontrado){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        }
    }

    private suspend fun calcularSalario(trabajador: Trabajador): Double{
        if (trabajador.salario > 0.00){
            return trabajador.salario
        }
        logger.info("Salario del trabajador con id ${trabajador.id} detectado como 0.00, calculando salario...")
        val salarioCalculado: Double
        //Por cada 3 años de antigüedad se incrementa el salario en 100€. Si no llega a 3 no se añade nada
        val antiguedad = LocalDate.now().year - trabajador.fechaContratacion.year
        val incrementoAntiguedad: Double = if (antiguedad >= 3) (antiguedad / 3) * 100.00 else 0.00
        //Si es responsable se incrementa el salario en 1000€
        val incrementoResponsable: Double = if (trabajador.isResponsable) 1000.00 else 0.00
        //Si especialidad = "ELECTRICIDAD" se incrementa el salario en 1800€, "MOTOR 1700€, "MECANICA" 1600€ y "INTERIOR" 1750€
        val incrementoEspecialidad: Double = when (trabajador.especialidad) {
            "ELECTRICIDAD" -> 1800.00
            "MOTOR" -> 1700.00
            "MECANICA" -> 1600.00
            "INTERIOR" -> 1750.00
            else -> 0.00
        }
        salarioCalculado = trabajador.salario + incrementoAntiguedad + incrementoResponsable + incrementoEspecialidad
        //Actualiza el salario en la base de datos
        logger.info("Salario del trabajador con id ${trabajador.id} calculado como $salarioCalculado, actualizándolo en la base de datos...")
        trabajador.salario = salarioCalculado
        trabajadorService.update(trabajador)
        logger.info("Salario actualizado correctamente")
        return salarioCalculado
    }

}