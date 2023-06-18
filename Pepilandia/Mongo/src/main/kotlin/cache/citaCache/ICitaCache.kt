package cache.citaCache

import cache.ICache
import models.Cita

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Interfaz de la caché de citas
 */
interface ICitaCache : ICache<String, Cita>