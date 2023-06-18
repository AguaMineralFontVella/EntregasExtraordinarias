import cache.citaCache.CitaCache
import cache.informeCache.InformeCache
import cache.propietarioCache.PropietarioCache
import cache.trabajadorCache.TrabajadorCache
import cache.vehiculoCache.VehiculoCache
import config.AppConfig.initConfig
import controller.*
import db.DataBaseManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import repository.citaRepository.CitaCachedRepository
import repository.citaRepository.CitaRepository
import repository.informeRepository.InformeCachedRepository
import repository.informeRepository.InformeRepository
import repository.propietarioRepository.PropietarioCachedRepository
import repository.propietarioRepository.PropietarioRepository
import repository.trabajadorRepository.TrabajadorCachedRepository
import repository.trabajadorRepository.TrabajadorRepository
import repository.vehiculoRepository.VehiculoCachedRepository
import repository.vehiculoRepository.VehiculoRepository

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

private val logger = KotlinLogging.logger {}

/**
 * Función principal
 * @param args Array<String>
 */
suspend fun main(args: Array<String>) = runBlocking {

    initConfig()

    val citaController = CitaController(CitaRepository(), CitaCachedRepository(CitaCache()))

    val controller = AppController(
        citaController,
        InformeController(InformeRepository(), InformeCachedRepository(InformeCache())),
        PropietarioController(PropietarioRepository(), PropietarioCachedRepository(PropietarioCache())),
        TrabajadorController(TrabajadorRepository(), TrabajadorCachedRepository(TrabajadorCache())),
        VehiculoController(VehiculoRepository(), VehiculoCachedRepository(VehiculoCache()))
    )

    CoroutineScope(Dispatchers.IO).launch {
        logger.info{" INICIO DE MONITOR DE CITAS "}
        citaController.citasFlow.collect { citas ->
            logger.info { "❗ NUEVO CAMBIO EN CITAS: $citas" }
        }
    }

    controller.run()
}
