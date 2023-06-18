package cache.informeCache

import cache.ICache
import models.Informe

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Interfaz de la caché de informes
 */
interface IInformeCache : ICache<String, Informe>