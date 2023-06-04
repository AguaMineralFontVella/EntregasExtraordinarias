package cache

import io.github.reactivecircus.cache4k.Cache
import models.CitaDTO
import kotlin.time.Duration

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Clase que gestiona la caché de citas
 * @property doRefresh Boolean
 * @property refreshTime Duration
 * @property size Long
 */
object CitaCacheManager {

    val doRefresh: Boolean = false
    private val refreshTime: Duration = Duration.INFINITE
    private const val size = 100L

    val cache = Cache.Builder()
        .maximumCacheSize(size)
        .expireAfterWrite(refreshTime)
        .build<Long, CitaDTO>()
}