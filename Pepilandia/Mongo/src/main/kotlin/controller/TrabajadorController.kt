package controller

import com.github.michaelbull.result.onFailure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.Trabajador
import mu.KotlinLogging
import repository.trabajadorRepository.ITrabajadorRepository
import repository.trabajadorRepository.TrabajadorCachedRepository
import validators.validar
import java.time.LocalDate

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

private val logger = KotlinLogging.logger {}

/**
 * Controlador de los trabajadores
 * @param trabajadorRepository Repositorio de los trabajadores
 * @param trabajadorCache Caché de los trabajadores
 */
class TrabajadorController(
    private val trabajadorRepository: ITrabajadorRepository,
    private val trabajadorCache: TrabajadorCachedRepository
    ) {

    /**
     * Función que devuelve todos los trabajadores
     * @return Flow de trabajadores
     */
    suspend fun findAllTrabajador() : Flow<Trabajador>? = withContext(Dispatchers.IO){
        val trabajadores = trabajadorCache.findAll()
            .map { trabajador ->
                val salario = calcularSalario(trabajador)
                trabajador.salario = salario
                trabajador }
            .toList()
        if (trabajadores.isEmpty()) {
            trabajadorRepository.findAll()
        } else {
            trabajadores.asFlow()
        }
    }

    /**
     * Función que devuelve un trabajador por su id
     * @param id Id del trabajador
     * @return Trabajador
     */
    suspend fun findTrabajadorById(id: Long): Trabajador? = withContext(Dispatchers.IO){
        val trabajador = trabajadorCache.findById(id)
        val salario = trabajador?.let { calcularSalario(it) }
        if (salario != null) {
            trabajador.salario = salario
        }
        if(trabajador == null) {
            trabajadorRepository.findById(id)
        } else {
            trabajador
        }
    }

    /**
     * Función que guarda un trabajador
     * @param trabajador Trabajador
     */
    suspend fun saveTrabajador(trabajador: Trabajador) = withContext(Dispatchers.IO){
        val listaTrabajadores = findAllTrabajador()?.toList()
        if (listaTrabajadores != null) {
            trabajador.validar(listaTrabajadores).onFailure { throw Exception("Error al validar el trabajador, asegúrate de que todos los campos son correctos") }
        }
        val salario = calcularSalario(trabajador)
        trabajador.salario = salario

        trabajadorRepository.save(trabajador)
        trabajadorCache.save(trabajador)

    }

    /**
     * Función que borra un trabajador
     * @param trabajador Trabajador
     * @return Booleano que indica si se ha borrado o no
     */
    suspend fun deleteTrabajador(trabajador: Trabajador): Boolean = withContext(Dispatchers.IO){
        try{
            trabajadorRepository.delete(trabajador)
            trabajadorCache.delete(trabajador)
            return@withContext true
        }
        catch (e: Exception){
            return@withContext false
        }
    }

    /**
     * Función que hace una serie de consultas sobre los trabajadores
     */
    suspend fun doConsultas() {
        logger.info { "TrabajadorController - doConsultas()" }
        val trabajadoresList = trabajadorRepository.findAll().toList().map { trabajador ->
            val salario = calcularSalario(trabajador)
            trabajador.salario = salario
            trabajador }
        val trabajadorMasGana = trabajadoresList.filter { !it.isResponsable }.maxByOrNull { it.salario }
        val salarioMedio = trabajadoresList.filter { !it.isResponsable }.map { it.salario }.average()
        val salarioMedioEspecialidad = trabajadoresList.groupBy { it.especialidad }.mapValues { it -> it.value.map { it.salario }.average() }
        val trabajadorMenosAntiguedad = trabajadoresList.minByOrNull { it.fechaContratacion }
        val trabajadoresOrdenados = trabajadoresList.sortedBy { it.especialidad }
        println("==== CONSULTAS ====")
        println(" ✅ El trabajador que más gana es: ${Json.encodeToString(trabajadorMasGana)}")
        println(" ✅ El salario medio de los trabajadores es: $salarioMedio")
        println(" ✅ El salario medio por especialidad es: $salarioMedioEspecialidad")
        println(" ✅ El trabajador con menos antigüedad es: ${Json.encodeToString(trabajadorMenosAntiguedad)}")
        println(" ✅ Los trabajadores ordenados por especialidad son: $trabajadoresOrdenados")
    }

    /**
     * Función que calcula el salario de un trabajador
     * @param trabajador: Trabajador
     * @return Double
     */
    private fun calcularSalario(trabajador: Trabajador): Double{
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
        logger.info("Salario actualizado correctamente")
        return salarioCalculado
    }

}