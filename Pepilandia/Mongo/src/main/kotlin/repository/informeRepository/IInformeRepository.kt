package repository.informeRepository

import models.Informe
import repository.CrudRepository

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Interfaz ICitaRepository
 */
interface IInformeRepository : CrudRepository<Informe, Long>