package repository.vehiculoRepository

import models.Vehiculo
import repository.CrudRepository

/**
 * @author Sergio Pérez Fernández
 * @mail sergio.perezfernandez@alumno.iesluisvives.org
 */

/**
 * Repositorio de la entidad Vehículo
 */
interface IVehiculoRepository: CrudRepository<Vehiculo, Long>