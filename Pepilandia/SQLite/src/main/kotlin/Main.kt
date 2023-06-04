import config.AppConfig
import config.AppConfig.initConfig
import controllers.*
import db.DatabaseManager.backup
import db.DatabaseManager.initData
import db.DatabaseManager.loadFromCSVPropietario
import db.DatabaseManager.loadFromCSVTrabajador
import db.DatabaseManager.loadFromCSVVehiculo
import kotlinx.coroutines.*
import locale.Locale.getFecha
import locale.Locale.getHoraAleatoria
import models.*
import repository.citaRepository.CitaRepositoryImpl
import repository.informeRepository.InformeRepositoryImpl
import repository.propietarioRepository.PropietarioRepositoryImpl
import repository.trabajadorRepository.TrabajadorRepositoryImpl
import repository.vehiculoRepository.VehiculoRepositoryImpl
import java.nio.file.Paths
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */
fun main(args: Array<String>) {


    val hourFormat = SimpleDateFormat("HH:mm")
    val propertiesFile = ClassLoader.getSystemResource("config.properties")

    initConfig()
    initData()

    //Iniciamos los controladores
    val citaController = CitaController(CitaRepositoryImpl())
    val informeController = InformeController(InformeRepositoryImpl())
    val propietarioController = PropietarioController(PropietarioRepositoryImpl())
    val trabajadorController = TrabajadorController(TrabajadorRepositoryImpl())
    val vehiculoController = VehiculoController(VehiculoRepositoryImpl())

    //Iniciamos el monitor de flujo de citas
    citasFlowMonitor(citaController)

    //Cargamos en la base de datos los datos de los CSV
    loadFromCSVVehiculo((Paths.get(propertiesFile.toURI()).parent).toString() + Paths.get(AppConfig.csvVehiculoPath).toString())
    vehiculoController.findAll()
    loadFromCSVPropietario((Paths.get(propertiesFile.toURI()).parent).toString() + Paths.get(AppConfig.csvPropietarioPath).toString())
    propietarioController.findAll()
    loadFromCSVTrabajador((Paths.get(propertiesFile.toURI()).parent).toString() + Paths.get(AppConfig.csvTrabajadorPath).toString())
    trabajadorController.findAll()
    //Se ejecuta una 2ª vez porque por motivos desconocidos el primer loadFromCSV (sea el que sea) se borra después del findAll() correspondiente. Al estar repetido ahora no se borra después del findAll().
    loadFromCSVVehiculo((Paths.get(propertiesFile.toURI()).parent).toString() + Paths.get(AppConfig.csvVehiculoPath).toString())
    vehiculoController.findAll()

    //Inicia el programa realizando unas consultas
    println("==== CONSULTAS ====")

    val trabajadoresList = trabajadorController.findAll().component1() as List<Trabajador>

    // - El trabajador que más gana sin ser responsable.
    val trabajadorMasGana = trabajadoresList.filter { !it.isResponsable }.maxByOrNull { it.salario }
    println(" ✅ El trabajador que más gana sin ser responsable es: $trabajadorMasGana")

    // - El salario medio de todos los trabajadores que no son responsables.
    val salarioMedio = trabajadoresList.filter { !it.isResponsable }.map { it.salario }.average()
    println(" ✅ El salario medio de todos los trabajadores que no son responsables es: $salarioMedio")

    // - El salario medio de todos los trabajadores agrupados por especialidad.
    val salarioMedioEspecialidad = trabajadoresList.groupBy { it.especialidad }.mapValues { it -> it.value.map { it.salario }.average() }
    println(" ✅ El salario medio de todos los trabajadores agrupados por especialidad es: $salarioMedioEspecialidad")

    // - La el trabajador/a con menos antigüedad.
    val trabajadorMenosAntiguedad = trabajadoresList.minByOrNull { it.fechaContratacion }
    println(" ✅ El trabajador/a con menos antigüedad es: $trabajadorMenosAntiguedad")

    // - Trabajadores ordenados por especialidad y ordenados
    val trabajadoresOrdenados = trabajadoresList.sortedBy { it.especialidad }
    println(" ✅ Trabajadores ordenados por especialidad y ordenados: $trabajadoresOrdenados")

    //Inicia la simulación
    println("==== INICIO DE PROGRAMA ====")

    val vehiculosList = vehiculoController.findAll().component1() as MutableList<Vehiculo>

    val horaActual = hourFormat.format(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
    val horaEnMediaHora = hourFormat.format(Date.from((LocalDateTime.now().plusMinutes(30)).atZone(ZoneId.systemDefault()).toInstant()))

    println("INTERVALO NUEVO: desde $horaActual hasta $horaEnMediaHora")

    //Contadores para controlar el número de citas por trabajador (Uno para cada uno)
    var countTrabajador1 = 0
    var countTrabajador2 = 0
    var countTrabajador3 = 0
    var countTrabajador4 = 0
    var countTrabajador5 = 0

    repeat(8){ //8 citas por intervalo, 4 máximas por trabajador
        val trabajadorAleatorio = trabajadoresList.random()

        when(trabajadorAleatorio.id){
            1 -> {
                countTrabajador1 += 1
                if (countTrabajador1 <= 4) {
                    if (countTrabajador1 == 4) {
                        println("El trabajador ${trabajadorAleatorio.id} lleva $countTrabajador1 citas y ha alcanzado el máximo número de citas para el intervalo")
                    }
                    else{
                        val vehiculoAleatorio = vehiculosList.random()
                        vehiculosList.remove(vehiculoAleatorio)
                        println("El trabajador ${trabajadorAleatorio.id} lleva $countTrabajador1 cita(s) y atenderá al vehículo: $vehiculoAleatorio")

                        getHoraAleatoria(horaActual, horaEnMediaHora)
                        val cita = Cita(
                            id = 0,
                            trabajadorAleatorio.id,
                            vehiculoAleatorio.id,
                            getFecha(),
                            getHoraAleatoria(horaActual, horaEnMediaHora)
                        )
                        citaController.save(cita)
                        val informe = informeController.hacerInforme(cita.id, trabajadorAleatorio.id, vehiculoAleatorio.id, vehiculoAleatorio.dniPropietario)
                        println("El trabajador ${trabajadorAleatorio.id} ha hecho el informe: $informe")
                        informeController.save(informe)
                    }
                }
                if (countTrabajador1 > 4){
                    println("El trabajador ${trabajadorAleatorio.id} ya tenía el máximo número de citas posibles, cediéndolo a otro trabajador")
                    return@repeat
                }
            }
            2 -> {
                countTrabajador2 += 1
                if (countTrabajador2 <= 4) {
                    if (countTrabajador2 == 4) {
                        println("El trabajador ${trabajadorAleatorio.id} lleva $countTrabajador2 citas y ha alcanzado el máximo número de citas para el intervalo")
                    }
                    else{
                        val vehiculoAleatorio = vehiculosList.random()
                        vehiculosList.remove(vehiculoAleatorio)
                        println("El trabajador ${trabajadorAleatorio.id} lleva $countTrabajador2 cita(s) y atenderá al vehículo: $vehiculoAleatorio")

                        val cita = Cita(
                            id = 0,
                            trabajadorAleatorio.id,
                            vehiculoAleatorio.id,
                            getFecha(),
                            getHoraAleatoria(horaActual, horaEnMediaHora)
                        )
                        citaController.save(cita)
                        val informe = informeController.hacerInforme(cita.id, trabajadorAleatorio.id, vehiculoAleatorio.id, vehiculoAleatorio.dniPropietario)
                        println("El trabajador ${trabajadorAleatorio.id} ha hecho el informe: $informe")
                        informeController.save(informe)
                    }
                }
                if (countTrabajador2 > 4){
                    println("El trabajador ${trabajadorAleatorio.id} ya tenía el máximo número de citas posibles, cediéndolo a otro trabajador")
                    return@repeat
                }
            }
            3 -> {
                countTrabajador3 += 1
                if (countTrabajador3 <= 4) {
                    if (countTrabajador3 == 4) {
                        println("El trabajador ${trabajadorAleatorio.id} lleva $countTrabajador3 citas y ha alcanzado el máximo número de citas para el intervalo")
                    }
                    else{
                        val vehiculoAleatorio = vehiculosList.random()
                        vehiculosList.remove(vehiculoAleatorio)
                        println("El trabajador ${trabajadorAleatorio.id} lleva $countTrabajador3 cita(s) y atenderá al vehículo: $vehiculoAleatorio")

                        val cita = Cita(
                            id = 0,
                            trabajadorAleatorio.id,
                            vehiculoAleatorio.id,
                            getFecha(),
                            getHoraAleatoria(horaActual, horaEnMediaHora)
                        )
                        citaController.save(cita)
                        val informe = informeController.hacerInforme(cita.id, trabajadorAleatorio.id, vehiculoAleatorio.id, vehiculoAleatorio.dniPropietario)
                        println("El trabajador ${trabajadorAleatorio.id} ha hecho el informe: $informe")
                        informeController.save(informe)
                    }
                }
                if (countTrabajador3 > 4){
                    println("El trabajador ${trabajadorAleatorio.id} ya tenía el máximo número de citas posibles, cediéndolo a otro trabajador")
                    return@repeat
                }
            }
            4 -> {
                countTrabajador4 += 1
                if (countTrabajador4 <= 4) {
                    if (countTrabajador4 == 4) {
                        println("El trabajador ${trabajadorAleatorio.id} lleva $countTrabajador4 citas y ha alcanzado el máximo número de citas para el intervalo")
                    }
                    else{
                        val vehiculoAleatorio = vehiculosList.random()
                        vehiculosList.remove(vehiculoAleatorio)
                        println("El trabajador ${trabajadorAleatorio.id} lleva $countTrabajador4 cita(s) y atenderá al vehículo: $vehiculoAleatorio")

                        val cita = Cita(
                            id = 0,
                            trabajadorAleatorio.id,
                            vehiculoAleatorio.id,
                            getFecha(),
                            getHoraAleatoria(horaActual, horaEnMediaHora)
                        )
                        citaController.save(cita)
                        val informe = informeController.hacerInforme(cita.id, trabajadorAleatorio.id, vehiculoAleatorio.id, vehiculoAleatorio.dniPropietario)
                        println("El trabajador ${trabajadorAleatorio.id} ha hecho el informe: $informe")
                        informeController.save(informe)
                    }
                }
                if (countTrabajador4 > 4){
                    println("El trabajador ${trabajadorAleatorio.id} ya tenía el máximo número de citas posibles, cediéndolo a otro trabajador")
                    return@repeat
                }
            }
            5 -> {
                countTrabajador5 += 1
                if (countTrabajador5 <= 4) {
                    if (countTrabajador5 == 4) {
                        println("El trabajador ${trabajadorAleatorio.id} lleva $countTrabajador5 citas y ha alcanzado el máximo número de citas para el intervalo")
                    }
                    else{
                        val vehiculoAleatorio = vehiculosList.random()
                        vehiculosList.remove(vehiculoAleatorio)
                        println("El trabajador ${trabajadorAleatorio.id} lleva $countTrabajador5 cita(s) y atenderá al vehículo: $vehiculoAleatorio")

                        val cita = Cita(
                            id = 0,
                            trabajadorAleatorio.id,
                            vehiculoAleatorio.id,
                            getFecha(),
                            getHoraAleatoria(horaActual, horaEnMediaHora)
                        )
                        citaController.save(cita)
                        val informe = informeController.hacerInforme(cita.id, trabajadorAleatorio.id, vehiculoAleatorio.id, vehiculoAleatorio.dniPropietario)
                        println("El trabajador ${trabajadorAleatorio.id} ha hecho el informe: $informe")
                        informeController.save(informe)
                    }
                }
                if (countTrabajador5 > 4){
                    println("El trabajador ${trabajadorAleatorio.id} ya tenía el máximo número de citas posibles, cediéndolo a otro trabajador")
                    return@repeat
                }
            }
        }
    }

    println("✅ Ya son las $horaEnMediaHora, intervalo finalizado, realizando copia de seguridad de la base de datos")

    //Realizamos copia de seguridad de la base de datos
    backup()

    println("✅ Copia de seguridad realizada, finalizando programa")
}

/**
 * Monitorea el flujo de citas
 * @param citaController controlador de citas
 */
fun citasFlowMonitor(citaController: CitaController)= GlobalScope.launch {
    println("INICIO DE MONITOR DE CITAS")
    citaController.citasFlow.collect { citas ->
        println("❗ NUEVO CAMBIO EN CITAS: $citas")
    }
}