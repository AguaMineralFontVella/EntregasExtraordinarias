package cache.trabajadorCache

import cache.ICache
import models.Trabajador

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Interfaz de la caché de trabajadores
 */
interface ITrabajadorCache : ICache<String, Trabajador>