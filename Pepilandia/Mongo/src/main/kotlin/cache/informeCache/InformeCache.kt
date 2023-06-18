package cache.informeCache

import io.github.reactivecircus.cache4k.Cache
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext
import models.Informe
import mu.KotlinLogging
import kotlin.time.Duration.Companion.minutes

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

private val logger = KotlinLogging.logger {  }

/**
 * Implementación de la interfaz de la caché de informes
 */
class InformeCache : IInformeCache{
    override val cacheDuration: Long = 1800000L // 30 minutos
    override val cache = Cache.Builder()
        .maximumCacheSize(50)
        .expireAfterAccess(1.minutes)
        .build<String, Informe>()

    suspend fun refresh(){
        withContext(newSingleThreadContext("Refrescador Cache Informe")) {
            launch {
                logger.info { "ACTUALIZANDO CACHE" }
                cache.invalidateAll()
                delay(cacheDuration)
            }
        }
    }

}