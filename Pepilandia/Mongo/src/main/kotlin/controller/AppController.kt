package controller

import db.DataBaseManager
import db.getPropietariosInit
import db.getTrabajadoresInit
import db.getVehiculosInit
import dto.CitaCreateDto
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.toList
import mappers.toCita
import mappers.toPropietario
import mappers.toTrabajador
import mappers.toVehiculo
import models.Trabajador
import mu.KotlinLogging
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import kotlin.system.exitProcess

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

private var logger = KotlinLogging.logger {}

/**
 * Controlador principal de la aplicación
 * @param citaController Controlador de las citas
 * @param informeController Controlador de los informes
 * @param propietarioController Controlador de los propietarios
 * @param trabajadorController Controlador de los trabajadores
 * @param vehiculoController Controlador de los vehículos
 */
class AppController(
    private val citaController : CitaController,
    private val informeController: InformeController ,
    private val propietarioController: PropietarioController,
    private val trabajadorController: TrabajadorController,
    private val vehiculoController: VehiculoController
) {

    private val hourFormat = SimpleDateFormat("HH:mm")

    suspend fun run(){
        logger.info { "Iniciando AppController" }

        logger.info { "Borrando datos en la base de datos MongoDB" }
        DataBaseManager.database.dropCollection("cita")
        DataBaseManager.database.dropCollection("informe")
        DataBaseManager.database.dropCollection("propietario")
        DataBaseManager.database.dropCollection("trabajador")
        DataBaseManager.database.dropCollection("vehiculo")
        logger.info { "Datos borrados, creando colecciones..."}
        DataBaseManager.database.createCollection("cita")
        DataBaseManager.database.createCollection("informe")
        DataBaseManager.database.createCollection("propietario")
        DataBaseManager.database.createCollection("trabajador")
        DataBaseManager.database.createCollection("vehiculo")
        logger.info { "Colecciones creadas" }

        logger.info { "Cargando datos iniciales" }
        val job1: Job = CoroutineScope(Dispatchers.IO).launch { getPropietariosInit().forEach{ propietarioController.savePropietario(it.toPropietario()) }}
        val job2: Job = CoroutineScope(Dispatchers.IO).launch { getTrabajadoresInit().forEach{ trabajadorController.saveTrabajador(it.toTrabajador()) }}
        val job3: Job = CoroutineScope(Dispatchers.IO).launch { getVehiculosInit().forEach{ vehiculoController.saveVehiculo(it.toVehiculo()) }}
        joinAll(job1, job2, job3)
        logger.info { "Datos iniciales cargados" }

        trabajadorController.doConsultas()

        println("==== INICIO DE LA SIMULACIÓN ====")

        val horaActual = hourFormat.format(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
        val horaEnMediaHora = hourFormat.format(Date.from((LocalDateTime.now().plusMinutes(30)).atZone(ZoneId.systemDefault()).toInstant()))

        println("INTERVALO NUEVO: desde $horaActual hasta $horaEnMediaHora")

        val listaTrabajadores = trabajadorController.findAllTrabajador()?.toList()
        val listaVehiculos = vehiculoController.findAllVehiculo()?.toList()
        val listaTrabajadoresMutable: MutableList<Trabajador> = mutableListOf()

        repeat(8){
            logger.info { "Creando cita..." }
            val trabajador = listaTrabajadores?.random()
            val vehiculo = listaVehiculos?.random()

            val cita = trabajador?.id?.let { it1 ->
                vehiculo?.id?.let { it2 ->
                    CitaCreateDto(
                        idTrabajador = it1,
                        idVehiculo = it2,
                        fecha = LocalDate.now().toString(),
                        hora = hourFormat.format(
                            Date.from(
                                (LocalDateTime.now().plusMinutes(30)).atZone(ZoneId.systemDefault()).toInstant()
                            )
                        ),
                    )
                }
            }

            if (cita != null) {
                listaTrabajadores.forEach{
                    listaTrabajadoresMutable.add(it)
                }
                citaController.addListaTrabajadores(listaTrabajadoresMutable)
                if (vehiculo != null) {
                    val horaAleatoria = getHoraAleatoria(horaActual, horaEnMediaHora)
                    cita.toCita(horaAleatoria).let {
                        citaController.saveCita(it)
                        val informe = informeController.hacerInforme(it.id, it.idTrabajador, it.idVehiculo, vehiculo.dniPropietario)
                        informeController.saveInforme(informe)
                    }
                }
            }

        }
        println("✅ Ya son las $horaEnMediaHora y se han realizado 8 citas. Intervalo finalizado")
        exitProcess(0)
    }


    fun getHoraAleatoria(horaActual: String, horaEnMediaHora: String): String {
        val horaActualSplit = horaActual.split(":")
        val horaEnMediaHoraSplit = horaEnMediaHora.split(":")
        val horaActualInt = horaActualSplit[0].toInt()
        val horaEnMediaHoraInt = horaEnMediaHoraSplit[0].toInt()
        val minutosActualInt = horaActualSplit[1].toInt()
        val minutosEnMediaHoraInt = horaEnMediaHoraSplit[1].toInt()
        val horaAleatoriaInt = (horaActualInt..horaEnMediaHoraInt).random()
        val minutosAleatoriosInt = if (horaAleatoriaInt == horaActualInt) {
            (minutosActualInt..59).random()
        } else if (horaAleatoriaInt == horaEnMediaHoraInt) {
            (0..minutosEnMediaHoraInt).random()
        } else {
            (0..59).random()
        }
        val horaAleatoria = if (horaAleatoriaInt < 10) { //Añadir un 0 al principio
            "0$horaAleatoriaInt"
        } else {
            "$horaAleatoriaInt"
        }
        val minutosAleatorios = if (minutosAleatoriosInt < 10) {
            "0$minutosAleatoriosInt"
        } else {
            "$minutosAleatoriosInt"
        }
        return "$horaAleatoria:$minutosAleatorios"
    }
}