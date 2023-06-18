package repository.citaRepository

import models.Cita
import repository.CrudRepository

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Interfaz ICitaRepository
 */
interface ICitaRepository : CrudRepository<Cita, Long>