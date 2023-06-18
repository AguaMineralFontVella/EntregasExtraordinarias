package repository.propietarioRepository

import models.Propietario
import repository.CrudRepository

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Repositorio de la entidad Propietario
 */
interface IPropietarioRepository : CrudRepository<Propietario, Long>