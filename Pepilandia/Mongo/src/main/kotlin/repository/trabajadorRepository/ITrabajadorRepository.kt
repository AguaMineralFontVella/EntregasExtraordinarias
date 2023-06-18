package repository.trabajadorRepository

import models.Trabajador
import repository.CrudRepository

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Repositorio de la entidad Trabajador
 */
interface ITrabajadorRepository : CrudRepository<Trabajador, Long>