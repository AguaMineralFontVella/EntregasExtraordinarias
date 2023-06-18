package cache

import io.github.reactivecircus.cache4k.Cache

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

interface ICache<ID : Any, T : Any> {
    val cacheDuration: Long
    val cache: Cache<ID, T>
}