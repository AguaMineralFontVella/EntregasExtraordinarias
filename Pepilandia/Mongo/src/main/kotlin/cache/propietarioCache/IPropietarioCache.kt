package cache.propietarioCache

import cache.ICache
import models.Propietario

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Interfaz de la caché de propietarios
 */
interface IPropietarioCache : ICache<String, Propietario>