package cache.vehiculoCache

import cache.ICache
import models.Vehiculo

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Interfaz de la caché de vehículos
 */
interface IVehiculoCache : ICache<String, Vehiculo>